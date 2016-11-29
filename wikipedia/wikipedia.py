# How will we download 5,000,000 articles?
#     Can we massively parallelize the download process by getting people to run it on their computers?
#     If so, we need to limit how fast we download so we don't put too much stress on Wikipedia's servers and/or get banned.
# Where will we start scraping?
# How do we avoid visiting the same page multiple times?
# How do we continuously update the database as Wikipedia updates itself and adds new articles?
# What do we do with redirected pages?

import urllib.request
from bs4 import BeautifulSoup
from urllib.parse import urlparse



def get_webpage(url):
    return urllib.request.urlopen(url).read()

def is_relevant_link(a):
    is_relevant = True
    href = a["href"]

    if not href.startswith("/wiki/"): is_relevant = False

    filters = [
        "/wiki/Category:",
        "/wiki/Special:",
        "/wiki/Help:",
        "/wiki/File:",
        "/wiki/Wikipedia:",
        "/wiki/Template:",
        "/wiki/Template_talk:"
    ]

    if any(start in href for start in filters): is_relevant = False

    return is_relevant

def get_canonicalized_link(link):
    # TODO Strip any trailing named anchor (example.com/link#section turns into example.com/link)
    # TODO Figure out how to deal with special characters (example: ' character turns into %27)
    return link



RANDOM_PAGE = "https://en.wikipedia.org/wiki/Special:Random"
TEST_PAGE = "https://en.wikipedia.org/wiki/St._John%27s_Red_Storm_baseball" # redirects to St. John's Red Storm#Baseball
#TEST_PAGE = "https://en.wikipedia.org/wiki/Anatoli_Blagonravov"

start_page = get_webpage(RANDOM_PAGE)
#start_page = get_webpage(TEST_PAGE)
start_soup = BeautifulSoup(start_page, "html.parser")

found_links = []
for a in start_soup.find("div", id="bodyContent").find_all("a"):
    if is_relevant_link(a):
        canonical = get_canonicalized_link(a["href"])
        found_links.append(canonical)



for link in found_links: print("\t%s" % link)
print("Document:       " + start_soup.title.string)
print("Links found:    " + str(len(found_links)))
