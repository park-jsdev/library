# This script will extract a table contained in an HTML file (web page) into Excel in a tabular form

# pip install pandas
# pip install bs4
# pip install openpyxl

import pandas as pd
from bs4 import BeautifulSoup

# Load the HTML document (e.g. save the desired HTML to an html file)
with open("2022PublicServiceEmployeeSurvey.html", encoding="utf-8") as file:
    soup = BeautifulSoup(file, "html.parser")

# Find all the `li` elements, change the HTML elements as desired
questions = soup.select("li > details > p.mrgn-tp-lg")
choices = soup.select("li > details > ol > li")

# Create a list to store the data
data = []

# Loop through the questions
for i, question in enumerate(questions):
    # Get the text of the question
    q = question.text

    # Get the text of the choices for this question
    c = [choice.text for choice in choices[i*7:(i+1)*7]]

    # Add the question and choices to the data list
    data.append([q] + c)

# Create a DataFrame from the data list, change the columns as desired
df = pd.DataFrame(data, columns=["Question", "Strongly agree", "Somewhat agree", "Neither agree nor disagree", "Somewhat disagree", "Strongly disagree", "Don’t know", "Not applicable"])

# Write the DataFrame to an Excel file
df.to_excel("file.xlsx", index=False)
