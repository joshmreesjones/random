Natural Language Processing with David Copperfield
==================================================
dc-nlp is a project utilizes natural language processing to create distant reading perspectives of David Copperfield. It was created for HON202 (Reading in the Digital Age) at NC State University, but it is being treated more as an independent project (because it's cool). There are three members of the programming team and four members of the reader/reviewer team, and this is the repository for one of the programers. Because the other two programmers are using Eclipse, I chose to have a separate repository for my own work so I can have complete control over the entire pipeline rather than relying on an IDE.

Tools
-----
dc-nlp uses:
- Apache OpenNLP to perform a textual analysis on David Copperfield
- Ant (build tool)
- D3.js (data visualization - eventually)
- Project Gutenberg for the [plaintext version](http://www.gutenberg.org/cache/epub/766/pg766.txt) of David Copperfield
- Python's SimpleHTTPServer

How to use
----------
- Gather book statistics
	- These repositories are private under `github.ncsu.edu` until someone wants to use them
- View the graphs
	- `cd` into the project's `site` directory
	- Run SimpleHTTPServer: `$ python -m SimpleHTTPServer`
	- Go to `localhost:8000/site/project-8.html` (check to see if your SimpleHTTPServer uses port 8000)

About the class
---------------
HON202, Reading in the Digital Age, is a class which invites students to ask important questions regarding the dramatic change digital media has brought to reading. Students examine various types of texts and reflect on our experience, all the while documenting what we have thought about and learned in a variety of ways. The fall 2014 section of HON202 is taught by Dr. Paul Fyfe, Victorianist and Eloquent Speaker, who is adored and appreciated by all who are graced with his majestic, flamboyant, exquisite vocabulary and diction.

About the project
-----------------
The main idea of this project is to generate several 'distant reading' perspectives on reading David Copperfield. Three visualizations/interpretations were planned and one assigned to each programmer. The goal of this part of the project is to take a text (any given text, not just David Copperfield) and generate an XKCD-like "novel narrative chart" (see the original [movie narrative charts](http://xkcd.com/657/large/)) showing proximity of characters over time. Such a visualization is created with D3.js, using [this project](http://csclub.uwaterloo.ca/~n2iskand/?page_id=13) as inspiration. After this visualization is produced, human readers/reviewers interpret the graph and add context by corresponding regions of the graph to specific events in the novel.

Results
-------
This project is still under development. Stay tuned!
