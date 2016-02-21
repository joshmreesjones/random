import requests
import os
from bs4 import BeautifulSoup

####################################################################

BASE_URL = 'http://jdl.liveresults.io'
START_URL = BASE_URL + '/tf/ucs-invitational#/2016-02-20'
OUTPUT_FILE = 'output.txt'

####################################################################

try:
    os.remove(OUTPUT_FILE)
except OSError:
    pass

output_file = open(OUTPUT_FILE, 'a')

####################################################################

def get_results_urls(start_url):
    r = requests.get(start_url)
    soup = BeautifulSoup(r.text, 'html.parser')
    results = []
    for a in soup.find_all('a', title='Startlist/Results'):
        results.append(BASE_URL + a['href'])
    return results

def get_results_text(page_url):
    r = requests.get(page_url)
    soup = BeautifulSoup(r.text, 'html.parser')
    div = soup.find('div', class_='ui attached fluid segment')
    pre = div.findChildren()[0]
    return pre.string

def append_to_file(results):
    results = results.replace('\r', '')
    output_file.write(results)

####################################################################

results_urls = get_results_urls(START_URL)
count = 1
for url in results_urls:
    results = get_results_text(url)
    append_to_file(results)
    print("Adding results #%d to file." % count)
    count += 1
