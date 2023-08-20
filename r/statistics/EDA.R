# Exploratory Data Analysis

# Basic setup
setwd("C:\\Users\\JP\\Desktop\\dirpath")
library(ggplot2) # Install Library, also you can install from packages (but should be automatic)
theme_set(theme_classic())

# Load dataset
df <- read.csv("data.csv")
summary(df)
head(df)

# See unique values of a col in a dataframe
unique(df$col)

# See how many times unique value appears in a column
table(df$col)


# R DataFrames
# dataframes an have different types of data in different columns


# Basic Usage:
df <- data.frame(column1 = c("A", "B", "C"),
                 column2 = c(1, 2, 3),
                 column3 = c(TRUE, FALSE, TRUE))
print(df)

# column1 column2 column3
# 1       A       1    TRUE
# 2       B       2   FALSE
# 3       C       3    TRUE

# Row names
df <- data.frame(column1 = c(1, 2, 3),
                 row.names = c("row1", "row2", "row3"))


# Accessing Data:
df$column1  # Using $
df[["column1"]]  # Using [[]]

df[1,]  # Accesses the first row

df[1, "column1"]  # Accesses the first row of 'column1'

