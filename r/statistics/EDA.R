# Exploratory Data Analysis

# For all statistical tests, make sure to select the appropriate one for the use case and the data.
# Ensure that assumptions of the statistical tests are met.

# Basic setup
setwd("C:\\Users\\JP\\Desktop\\dirpath")
library(ggplot2) # Install Library, also you can install from packages (but should be automatic)
theme_set(theme_classic())

# Load dataset
df <- read.csv("data.csv")
summary(df)
head(df)

# See column names
names(df) # more general purpose (vector or list)
colnames(df)

# See unique values of a col in a dataframe
unique(df$col)

# See how many times unique value appears in a column
table(df$col)

# Detailed summary of dataframe
str(df)

# Check if dataframe or another class
class(df)

# Convert back to df
df <- as.data.frame(df)

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


# Numerical Analysis of Variables

# Outlier Detection in Variables

# Scatter Plot: A scatter plot can provide a good initial sense of the data distribution and potential outliers.
plot(x, y)

# Boxplot: This shows you the interquartile range and any data points that fall far from the other points.
boxplot(x)
boxplot(y)

# Statistical Measures

# Z-Score: The Z-score represents how many standard deviations an element is from the mean.
# A Z-score above a certain absolute value (commonly 2 or 3) could be considered an outlier.
z_scores_x <- scale(x)
z_scores_y <- scale(y)

# Filtering outliers
juncoNoOutlier <- filter(junco, percentWhiteTail > 38)

# E.g. plot with filter
ggplot(junco, aes(latitude, percentWhiteTail)) +
  geom_point(size = 2, col = "firebrick") +
  geom_smooth(method = "lm", se = FALSE, col = "firebrick") +
  geom_point(size = 2, data = juncoNoOutlier) +
  geom_smooth(method = "lm", se = FALSE, col = "black",
              data = juncoNoOutlier) +
  labs(x = "Latitude (degrees N)", y = "Percent white in tail")



# Shapiro-Wilk Test for normality:
# This is considered a "formal test" for normality
# W close to 1 means it does not deviate much from normality
# In this test, the alternative hypothesis is that it deviates from normality, therefore a p > 0.05 means
# that the distribution is indeed normal.
shapiro.test(variable)

# Skewness and Kurtosis:
# Calculate skewness and kurtosis to check for asymmetry and tail behavior.
library(moments)
skewness(variable)
kurtosis(variable)

# Levene's Test
# Used in regression to check for homogeneity of variances (homoscedasticity).
library(car)
leveneTest(y_variable ~ x_variable)

# Residual Plots:
# This is a model diagonostic. In regression models, residual plots can be helpful to identify the need for transformation.
plot(model$residuals)

# Box-Cox Transformation (catch-all method)
library(MASS)
boxcox(model)


# Common Transformations
# Log Transformation: log(variable)
# Square Root: sqrt(variable)
# Inverse: 1 / variable


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



# Analysis of Relationships

# Contingency Analysis

# Confirm the column to be filtered
unique(chickadeeData$Source)

# Filter df for Source = feather
df_feather <- filter(chickadeeData, Source == 'feather')
df_feather

# Check the order of the categories
chickadeeData$CommRich
unique(chickadeeData$CommRich)
# They are already ordered

# Order the categories as desired by turning into a factor variable (if needed. This is useful for more than 2 categories)
CommRich_BirdSex_table$CommRich <-
  factor(CommRich_BirdSex_table$CommRich,
         levels = c("low", "high"))

# Create 2x2 Contingency Table for community richness and sex
CommRich_BirdSex_table <- table(df_feather$CommRich, df_feather$BirdSex)
CommRich_BirdSex_table
str(CommRich_BirdSex_table)

# Create a mosaic plot to visualize the data
par(pty = "s") # makes a square plot
mosaicplot(t(CommRich_BirdSex_table), col = c("firebrick", "goldenrod1"),
           cex.axis = 1, main = "",
           sub = "Infection status", ylab = "Relative frequency")

# Conduct a Chi-squared Test of Independence of Two Categorical Variables
Xsq <- chisq.test(CommRich_BirdSex_table, correct = FALSE)
Xsq


# Scatter Plot with linear model
ggplot(chickadeeData, aes(x=Habitat, y=PathRich)) +
  geom_point(aes(color=Habitat), size=3) +            # Scatter plot
  geom_smooth(method='lm', aes(group=1, color=Habitat)) + # Add a linear fit, colored by Habitat
  labs(title="Relationship between Pathogen Richness and Habitat",
       x="Habitat",
       y="Pathogen Richness") +
  theme_minimal()


# Regression Analysis

# Simple Linear Regression
PathRich_Habitat_Regression <- lm(PathRich ~ Habitat, data = chickadeeData)
# The coefficient estimates tells us how much the dependent variable changes based on a change of 1 in the independent variable
# The Multiple R-squared tells us the proportion of the variance in the dependent
# variable that is predictable from the independent variable(s)
summary(PathRich_Habitat_Regression)

# Calculate a 95% Confidence interval for the slope
confint(PathRich_Habitat_Regression, level = 0.95)

# Multiple Linear Regression
# Checks the significance of the entire model, you can check between 2 predictor variables and compare.
fit_all <- lm(PathRich ~ WingChord + BirdWeight + TailLen + TarsusLen, data = df_feather)
# Residuals, RSE (RMSE) and R-squared are all indicators of a model's goodness of fit
summary(fit_all)

# Residual analysis
# residuals—the differences between observed and predicted values

# Magnitue of Error from RSE
# The RSE gives you an idea of how much your predictions are likely to deviate from the actual values.
# Lower RSE values indicate better fit, as the predictions are closer to the observed data,
# whereas higher values suggest that the model predictions deviate more from the observed data.

# Residual Distribution
# In an ideal scenario for linear regression, residuals should be normally distributed with a mean of zero.
# The RSE gives an idea of the "spread" of these residuals.

# Prediction Intervals from Residuals
# The RSE is used to calculate prediction intervals for individual predictions.
# A prediction interval gives an interval within which a new observation will fall with a certain probability,
# assuming the model is correct.

# Analysis of Variance

# ANOVA
# Essentially tests whether the model, as a whole, explains a significant amount of the variance
# in the dependent variable.
anova(PathRich_Habitat_Regression)

# Kruskal-Wallis Test
# A nonparametric method based on ranks, is the equivalent of the Mann–Whitney U-test when there are more than
# two groups.
# For testing differences among k populations in the means or medians of their distributions.
kruskal.test(PathRich ~ Habitat, data = chickadeeData)

# Tukey-Kramer
# Unplanned Comparisons (Multiple Comparisons Between All Pairs of Means)
# Often used as a post-hoc analysis after a one-way ANOVA or Kruskal-Wallis test to determine which specific groups differ from each other.
pathogenPairs <- emmeans(PathRich_Habitat_Regression, specs = "Habitat")
pathogenUnplanned <- contrast(pathogenPairs, method = "pairwise",
                              adjust = "tukey")

pathogenUnplanned

# Outlier Detection in Linear Regression

# Cook's Distance: This measure identifies points that are particularly influential in 
# calculating the fitted regression line.
model <- lm(y ~ x)
cook_dist <- cooks.distance(model)
influential_points <- as.numeric(names(cook_dist)[cook_dist > (4/n)])

# Standardized Residuals: After fitting the initial model, examine the standardized residuals
# for values that are too high or too low.
model <- lm(y ~ x)
std_residuals <- rstandard(model)

