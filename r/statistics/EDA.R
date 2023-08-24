# Exploratory Data Analysis

# Basic setup
setwd("C:\\Users\\JP\\Desktop\\dirpath")
library(ggplot2) # Install Library, also you can install from packages (but should be automatic)
theme_set(theme_classic())

# Load dataset
df <- read.csv("data.csv")
summary(df)
head(df)

# See column names
names(df)

# See unique values of a col in a dataframe
unique(df$col)

# See how many times unique value appears in a column
table(df$col)

# Detailed summary of dataframe
str(df)

# Summarize each variable using Hmisc
install.packages("Hmisc")
library(Hmisc)

describe(data)


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


# Summary Statistics



# Visualizations

# Factor the categorical only
chickadeeData$EscShi_ordered <- factor(chickadeeData$EscShi,
                                       levels = names(sort(chickadeeTableEscShi,
                                                           decreasing = TRUE)))

table(chickadeeData$EscShi_ordered)


# Visualize frequency distributions of single variables

# Bar charts for categorical variables
ggplot(data = chickadeeData, aes(x = Habitat)) +
  geom_bar(stat = "count") +
  labs(x = "Habitat", y = "Frequency") +
  theme(axis.text.x = element_text(angle = 90, hjust = 1))

# Histograms for numerical variables, note binwidth

# EscShi
ggplot(data = chickadeeData, aes(x = EscShi)) +
  geom_histogram(col = "black", binwidth = 50,
                 boundary = 0, closed = "left") +
  labs(x = "EscShi", y = "Frequency")

# PathRich
ggplot(data = chickadeeData, aes(x = PathRich)) +
  geom_histogram(col = "black", binwidth = 4,
                 boundary = 0, closed = "left") +
  labs(x = "Alpha Diversity", y = "Frequency")

# WingChord
ggplot(data = chickadeeData, aes(x = WingChord)) +
  geom_histogram(col = "black", binwidth = 1,
                 boundary = 0, closed = "left") +
  labs(x = "Wing Size", y = "Frequency")


# Relationship between 2 variables

# Box Plot for numerical variable vs categorical variable
ggplot(chickadeeData, aes(x=BirdSex, y=TarsusLen)) + geom_boxplot()

# Scatter Plots
ggplot(chickadeeData,   
       aes(x = BirdWeight, 
           y = EscShi)) +
  geom_point()

# Strip Chart
ggplot (data = chickadeeData, aes(x = WingChord, y = Entero)) +
  geom_jitter(shape = 1, size = 2, width = 0.15) +
  labs(x = "Wing Size", y = "Entero")

# Violin Plot
ggplot(data = chickadeeData, aes(y = BirdWeight, x = TailLen)) +
  geom_violin() +
  labs(x = "Tail Length", y = "BirdWeight") +
  stat_summary(fun = mean, geom = "point")