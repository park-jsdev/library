# Data Cleaning

# Basic setup
setwd("C:\\Users\\JP\\Desktop\\dirpath")

# Check for Missing Data in the Entire Dataset
sum(is.na(mydata))

# Check for Missing Data Column-wise
colSums(is.na(mydata))

# Check for Missing Data Row-wise
rowSums(is.na(mydata))

# Visualizing Missing Data
install.packages("visdat")
library(visdat)
vis_miss(mydata)
