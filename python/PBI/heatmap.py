# @author: Junsoo Park, junsoo.park@ec.gc.ca
import numpy as np 
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

# in PBI you simply load the data source once, then drag the columns you want as 'Values' then you can use them by calling 'dataset'.
df = dataset

#pd.read_excel ("C:\\Users\\Park Ju\\Desktop\\projects\\PSES2023\\Branch_Sentiment_Over_Time.xlsx")
#print(df.head (10)
# 113x14 for this particular example file after cleaning

# deal with outliers
df['SCORE5'] = pd.to_numeric(df['SCORE5'], errors='coerce')
df = df.dropna (subset=['SCORE5'])
threshold = 5

#filter rows based on condition
df = df.loc[df['SCORE5'] <= threshold]
df.sort_values('QUESTION')
pivot = pd.pivot_table(data=df, index='QUESTION', columns='BRANCH_ES', values = 'SCORE5')


# Values are aggregated by mean by default
#print (pivot)

values = pivot.values
labels = values.reshape(29,14)
#print(labels)

# Define the plot
fig, ax = plt.subplots(figsize=(30,24))

# Add title to the Heat map
title = "Sentiment Analysis by Branch"

# Set the font size and the distance of the title from the plot plt.title(title,fontsize=18)
plt.title(title, fontsize=18)
ttl = ax.title
ttl.set_position([0.5,1.05])

# Hide ticks for X & Y axis
ax.set_xticks([])
ax.set_yticks([])

#Remove the axes
#ax.axis('off')

# Use the heatmap function from the seaborn package
colors = sns.color_palette('coolwarm', as_cmap=True).reversed()
sns.heatmap(pivot, annot-labels, fmt='.1f',cmap=colors,linewidths=0.10, ax=ax, vmin=2.5,vmax=5)

#plt.title("% by Branch and Question")
plt.xlabel('Branch')
plt.ylabel('Question')
plt.show()