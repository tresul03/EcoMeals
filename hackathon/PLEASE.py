from flask import Flask, request
from urllib.request import urlopen, Request
from fuzzywuzzy import fuzz
import pandas as pd
import requests
import re
from selenium import webdriver
from time import sleep
from deepface import DeepFace
import cv2


df = pd.read_csv("SortedData.csv")
app = Flask(__name__)

@app.route("/barcode/<barcode>")
def goupc(barcode):
    url = f"https://go-upc.com/search?q={barcode}"
    req = Request(url=url, headers={"User-Agent":
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36'})

    html = urlopen(req).read().decode('utf-8')
    name = html.split('product-name">')[1].split("</h1>")[0]
    brand = html.split("Brand")[1].split("<td>")[1].split("</td>")[0]

    return f"{name},{brand}"

@app.route("/faceid")
def faceid(path):
    user = ""
    return Deepface.verify(path, user)

def metric(score):
    return 100/(score+1)

def getProductEmission(name, brand):
    url = f"https://api.ditchcarbon.com/v1.0/product?name={name}&manufacturer={brand}&price_cents=150&price_currency=GBP"

    headers = {
        "accept": "application/json",
        "authorization": "Bearer f79c7c8767a713b570a84946eacc014b"
    }

    response = requests.get(url, headers=headers)
    emission = response.json()["kgco2"]

    return emission

def getBrandEmission(brand):
    url = f"https://api.ditchcarbon.com/v1.0/supplier?name={brand}&currency=GBP"

    headers = {
        "accept": "application/json",
        "authorization": "Bearer f79c7c8767a713b570a84946eacc014b"
    }

    response = requests.get(url, headers=headers)
    try:
        emission = metric(response.json()['ef_kg_co2eq'])
    except:
        emission = 1

    return emission

@app.route("/ditch")
def ditch():
    name  = request.args.get("name", None)
    brand  = request.args.get("brand", None)
    return str(getProductEmission(name, brand))

def getHtml(url):
    req = Request(url=url, headers={"User-Agent":
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36'})
    html = urlopen(req).read().decode('utf-8')
    return html

def getInfo(url):
    html = getHtml(url)
    brand = html.split('itemprop="brand" ')[1].split(">")[1].replace("</a","")
    try:
        name = html.split('<span itemprop="description">')[1].split("</span>")[0]
            
    except:
        name = html.split('itemprop="name">')[1].split("<")[0]
        if " - " in name:
            name = name.split(" - ")[0]

    if "," in name:
        name = name.split(",")[0]

    if "," in brand:
        brand = brand.split(",")[0]
        
    return (brand, name)


@app.route("/openfood/<ingredient>")
def openfood(ingredient):
    driver = webdriver.Chrome(executable_path=r"chromedriver.exe")
    driver.get(f'https://uk.openfoodfacts.org/cgi/search.pl?search_terms={ingredient}&search_simple=1&action=process')
    sleep(0.5)

    try: checkbox = driver.find_element("id","preferences_switch_in_list_of_products")
    except:
        sleep(2)
        checkbox = driver.find_element("id","preferences_switch_in_list_of_products")
    driver.execute_script("arguments[0].click();", checkbox)
    links = [driver.find_element("xpath", f'//*[@id="products_match_all"]/li[{i}]/a').get_attribute('href')
             for i in range(1,4)]
    r = [getInfo(url) for url in links]
    driver.close()

    name = ""
    brand = ""
    rank = 0
    for info in r:
        emission = 0
        foundDf = False
        for index, row in df.iterrows():
            if fuzz.ratio(info[0], row["Company"]) > 49:
                emission = row["Adjust_Ratings"]
                foundDf = True
                break

        if not foundDf:
            emission = getBrandEmission(info[0]) 

        if emission > rank:
          brand = info[0]
          name = info[1]

    return f"{name} ({brand})"
        

@app.route("/transformer/<ingredients>")
def transformer(ingredients):
    ingredients = ingredients.split(",")
    better = [openfood(i) for i in ingredients]

    print()

    API_URL = "https://api-inference.huggingface.co/models/flax-community/t5-recipe-generation"
    headers = {"Authorization": f"Bearer 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"}

    def query(payload):
        response = requests.post(API_URL, headers=headers, json=payload)
        return response.json()

    output = query({
        "inputs": ingredients,
    })

    try:
        text = output[-1]['generated_text']
        for i in range(len(ingredients)):
            text = text.replace(ingredients[i], better[i])
            
        return f"{text};{better}"
    
    except:
        return "0"

if __name__ == "__main__":
  app.debug = False
  app.run(port=5000, host="0.0.0.0")
