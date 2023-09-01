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

# Centering and Scaling Data
apply(turtles[,-1], 2, mean)
apply(turtles[,-1], 2, sd)
scaledTurtles <- scale(turtles[, -1])

apply(scaledTurtles, 2, mean)
apply(scaledTurtles, 2, sd)

ggplot(data.frame(scaledTurtles, sex = turtles[,1]),
       aes(x = width, y = height, group = sex)) +
  geom_point(aes(color = sex), size = 3)

# Dimension Reduction

# Singular Value Decomposition (SVD) to find Principal Components

# Plotting Observations in the Principal Plane

# Principal Component Analysis
# We can perform PCA with princomp, prcomp, or dudi.pca

# Quick PCA

# Gather the numeric variables
numeric_variables <- chickadeeData[, c("WingChord", "BirdWeight", "TailLen", "TarsusLen")]

scaledBirds <- data.frame(scale(numeric_variables))

ggplot(scaledBirds, aes(x = WingChord, y = BirdWeight)) +
  geom_point(size = 2, shape = 21) +
  geom_point(aes(y = 0), colour = "red") +
  geom_segment(aes(xend = WingChord, yend = 0),
               colour = "red",
               arrow = arrow(length = unit(0.15, "cm")))

scaledBirds_clean_rows <- na.omit(scaledBirds)
# clustered heatmap of correlations
pheatmap(cor(scaledBirds_clean_rows), treeheight_row = 0.2)

# Perform PCA
pca_result <- prcomp(scaledBirds_clean_rows, center = TRUE, scale. = TRUE) 
# center and scale. are usually TRUE for standardized data, but since the data is already scaled, they are not strictly necessary

summary_pca <- summary(pca_result)
print(summary_pca)

# Explain the first 2 principal components (rotation matrix)
pca_result$rotation[, 1:2]

pca_result$sdev # standard deviations
pca_result$sdev^2 # eigenvalues/variances
pca_result$sdev^2 / sum(pca_result$sdev^2) # proportion of variance

get_eig(pca_result) # eigenvalues/variances
fviz_eig(pca_result, geom = "bar", bar_width = 0.4) +
  ggtitle("")

# Scree Plot
fviz_eig(pca_result, addlabels = TRUE)

# Biplot of attributes
fviz_pca_var(pca_result, col.var = "black")

# Contribution of each variable
fviz_cos2(pca_result, choice = "var", axes = 1:2)

# Biplot combined with cos2
fviz_pca_var(pca_result, col.var = "cos2",
             gradient.cols = c("black", "orange", "green"),
             repel = TRUE)

# PCA Scatter Plot
chickadee_clean_rows <- na.omit(chickadeeData) # we need to ensure the rows are equal from the dataset not used in the pca

fviz_pca_ind(pca_result, habillage = chickadee_clean_rows$BirdSex,
             geom = "point") +
  ggtitle("") +
  ylim(c(-6.5,7.5)) +
  coord_fixed()

# PCA BiPlot
fviz_pca_biplot(pca_result, geom = "point",
                habillage = chickadee_clean_rows$BirdSex,
                col.var = "violet", addEllipses = TRUE,
                ellipse.level = 0.69) +
  ggtitle("") +
  ylim(c(-4,5)) +
  coord_fixed()


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

# Means

# Two-sample t-test
# Determine whether the mean of the dependent variable differs significantly between the independent variables
# i.e. compare the means of two groups to see if they are statistically different.

# Assess normality and equal variance
ggplot(df_feather, aes(x = PathRich)) +
  geom_histogram(col = "black", binwidth = 3,
                 boundary = 0, closed = "left") +
  facet_wrap( ~ BirdSex, ncol = 1, scales = "free_y") +
  labs(x = "Pathogen richness", y = "Sex")

# Q-Q Plot (Quantile-Quantile Plot)
#  If the data points closely follow the line in a Q-Q plot, the data are approximately normally distributed.
qqnorm(df_feather$PathRich)
qqline(df_feather$PathRich)

# Levene's Test to check for equal variance
leveneTest(df_feather$PathRich ~ df_feather$BirdSex) # fail to reject the null hypothesis, suggesting it is reasonable to assume equal variances.

# Check for missing values
sum(is.na(df_feather$PathRich)) # none

# Filter missing values (if there are any)
#featherSub <- filter(df_feather, PathRich != "NA")

# t-test assuming equal variance
t.test(PathRich ~ BirdSex, data = df_feather, var.equal = TRUE)

# t-test (Welch's t-test) with unequal variances
t.test(PathRich ~ BirdSex, data = df_feather, var.equal = FALSE)



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


# Analysis of Covariance (ANCOVA)
# Allows you to compare the difference in means between different groups while controlling for
# the variability of other variables (covariates).

# RSS: The residual sum of squares for each model. 
# Smaller values generally indicate a better fit to the data, although this needs to be considered
# alongside other metrics.

# Pr(>F): The p-value for the F-test. A small p-value (<0.05) would suggest that the interaction term is statistically significant.

# Scatterplot by Sex
ggplot(df_feather, aes(WingChord, PathRich, shape = BirdSex)) +
  geom_point(size = 2) +
  scale_shape_manual(values = c(16, 1)) +
  labs(x = "Wing Chord", y = "Pathogen Richness")

# Fit the main effects model (with no interaction term)
featherNoInteractModel <- lm(PathRich ~ WingChord + BirdSex,
                             data = df_feather)
df_feather$fit0 <- predict(featherNoInteractModel)
ggplot(df_feather, aes(WingChord, PathRich, colour = BirdSex,
                       shape = BirdSex, linetype=BirdSex)) +
  geom_line(aes(y = fit0), size = 1, color = "black") +
  geom_point(size = 2) +
  scale_colour_manual(values = c("black", "black")) +
  scale_shape_manual(values = c(16, 1)) +
  labs(x = "Wing Chord", y = "Pathogen Richness")

summary(featherNoInteractModel)

# Fit the interaction model
featherInteractModel <- lm(PathRich ~ WingChord * BirdSex,
                           data = df_feather)
ggplot(df_feather, aes(WingChord, PathRich, colour = BirdSex,
                       shape = BirdSex, linetype=BirdSex)) +
  geom_smooth(method = "lm", size = 1, se = FALSE, col = "black") +
  geom_point(size = 2) +
  scale_colour_manual(values = c("black", "black")) +
  scale_shape_manual(values = c(16, 1)) +
  labs(x = "Wing Chord", y = "Pathogen Richness")

summary(featherInteractModel)

# ANOVA table
anova(featherNoInteractModel, featherInteractModel)
# No significant difference


# Multivariate Analysis

# Correlation Matrix
# we must ensure that we select only numerical columns
cor_matrix <- cor(my_data)

# Manually specifying columns indices or names:
# Using column indices
cor_matrix <- cor(my_data[, c(1, 3, 4)])

# Using column names
cor_matrix <- cor(my_data[, c("var1", "var2", "var3")])

# Automatically detect and select numeric indices
library(dplyr)

# Select only numeric columns
numeric_data <- select_if(my_data, is.numeric)

# Calculate the correlation matrix
cor_matrix <- cor(numeric_data)

# Pick column pairs

# Scatter plot matrix (pairs)
ggpairs(chickadeeData[, c("EscShi", "Entero")], axisLabels = "none") +
  theme_bw()

# Clustered Heatmap of Correlations between variables
numeric_variables <- chickadeeData[, c("EscShi", "Entero", "PathRich")]
pheatmap(cor(numeric_variables), cell.width = 10, cell.height = 10)


# Visualize a correlation matrix by melting

# Melt the correlation matrix to long format
melted_cormat <- melt(cor_matrix)


# Create the heatmap using ggplot2
ggplot(data = melted_cormat, aes(x=Var1, y=Var2)) +
  geom_tile(aes(fill = value), color = "white") +
  scale_fill_gradient2(low = "blue", high = "red", mid = "white", 
                       midpoint = 0, limit = c(-1,1), space = "Lab", 
                       name="Pearson\nCorrelation") +
  theme_minimal() +
  theme(axis.text.x = element_text(angle = 45, vjust = 1, size = 12, hjust = 1)) +
  coord_fixed()


# Linear Regression Using Principal Components

# Extract the principal component scores
pc1_scores <- pca_result$x[, 1]
pc2_scores <- pca_result$x[, 2]

# Fit a linear model to each relationship
fit_PC1 <- lm(chickadee_clean_rows$PathRich ~ pc1_scores)

# Make predictions using the linear model
chickadee_clean_rows$pc1_predicted <- predict(fit_PC1, newdata = chickadee_clean_rows)

# Scatterplot by Sex
ggplot(chickadee_clean_rows, aes(x = pc1_scores, y = PathRich)) +
  geom_point(aes(shape = BirdSex, color = BirdSex), size = 2) + 
  scale_shape_manual(values = c(16, 1)) +
  geom_line(aes(y = pc1_predicted), size = 1, color = "black") + 
  labs(x = "Dimension 1", y = "Pathogen Richness") +
  scale_colour_manual(values = c("red", "blue"))


# Make predictions using the previous simple linear model
fit_WingChord_cleaned <- lm(chickadee_clean_rows$PathRich ~ chickadee_clean_rows$WingChord)
chickadee_clean_rows$predicted_WingChord <- predict(fit_WingChord_cleaned, newdata = chickadee_clean_rows)

# Scatterplot by Sex with both models (along Dimension 1)
ggplot(chickadee_clean_rows, aes(x = pc1_scores, y = PathRich)) +
  geom_point(aes(shape = BirdSex, color = BirdSex), size = 2) + 
  scale_shape_manual(values = c(16, 1)) +
  geom_line(aes(y = pc1_predicted), size = 1, color = "black") + 
  geom_line(aes(x = pc1_scores, y = predicted_WingChord), size = 1, color = "orange", linetype = "dashed") +
  labs(x = "Dimension 1", y = "Pathogen Richness") +
  scale_colour_manual(values = c("red", "blue"))

# Summarize the fits
summary(fit_PC1)
summary(fit_WingChord_cleaned)
summary(fit_WingChord)


# Multidimensional Scaling (MDS) and Ordination

# Microbial community composition data for 2607 taxa was also used to calculate Bray Curtis dissimilarities between the 47 sites
distChickadee <- read.csv("ChickadeeDissimilarities.csv", row.names=1)
distChickadee[1:6, 1:6]

# clustered heatmap
pheatmap(distChickadee[1:12, 1:12], cluster_rows = TRUE,
         treeheight_row = 0.0001, treeheight_col = 0.8,
         fontsize_col = 8, cellwidth = 13, cellheight = 13)

# Classical (metric) multidimensional scaling (MDS), also known as principal coordinates analysis
MDSChickadee <- cmdscale(distChickadee, eig = TRUE)

# Create a plotbar function to plot the eigenvalues in a scree plot.
plotbar <- function(res, m = 9) {
  ggplot(data.frame(list(eig = res$eig[seq_len(m)],
                         k = seq(along = res$eig[seq_len(m)]))),
         aes(x = k, y = eig)) +
    scale_x_discrete("k", limits = factor(seq_len(m))) +
    theme_minimal() +
    geom_bar(stat="identity", width=0.5, color="orange",
             fill="pink")
}

plotbar(MDSChickadee, m = 5)

# Project the cities onto the first two coordinates created from the distances.
MDSChick <- data.frame(list(PCo1 = MDSChickadee$points[, 1],
                            PCo2 = MDSChickadee$points[, 2],
                            labs = rownames(MDSChickadee$points)))
ggplot(MDSChick, aes(x = PCo1, y = PCo2, label = labs)) +
  geom_point(color = "red") +
  #xlim(-1950, 2000) +
  #ylim(-1150, 1200) +
  coord_fixed() +
  geom_text_repel(size = 4, max.overlaps = 100)

#To re-orient the “map” so north is at the top and west is on the left, reverse the signs of the principal coordinates:
ggplot(MDSChick, aes(x = -PCo1, y = -PCo2, label = labs)) +
  geom_point(color = "red") +
  coord_fixed() +
  geom_text_repel(size = 4, max.overlaps = 100)

# Merge the datasets
MDSChick_merged <- merge(MDSChick, chickadeeData, by.x = "labs", by.y = "Site")
print(MDSChick_merged)

# By Habitat
ggplot(MDSChick_merged, aes(x = PCo1, y = PCo2)) +
  geom_point(aes(shape = Habitat)) +
  coord_fixed() +
  scale_shape_manual(values = c(16, 17, 18))

# Zoom in
ggplot(MDSChick_merged, aes(x = PCo1, y = PCo2)) +
  geom_point(aes(shape = Habitat)) +
  xlim(-0.25, 0.25) +
  ylim(-0.25, 0.25) +
  coord_fixed() +
  scale_shape_manual(values = c(16, 17, 18))

# By Source
ggplot(MDSChick_merged, aes(x = PCo1, y = PCo2)) +
  geom_point(aes(shape = Source)) +
  coord_fixed() +
  scale_shape_manual(values = c(16, 17))

# Zoom in
ggplot(MDSChick_merged, aes(x = PCo1, y = PCo2)) +
  geom_point(aes(shape = Source)) +
  xlim(-0.25, 0.25) +
  ylim(-0.25, 0.25) +
  coord_fixed() +
  scale_shape_manual(values = c(16, 17))

# Faceting by Habitat
ggplot(MDSChick_merged, aes(x = PCo1, y = PCo2)) +
  geom_point(aes(shape = Source)) +
  facet_wrap(~ Habitat) 

# Faceting by Source
ggplot(MDSChick_merged, aes(x = PCo1, y = PCo2)) +
  geom_point(aes(shape = Habitat)) +
  facet_wrap(~ Source) 

# Faceting by both
ggplot(MDSChick_merged, aes(x = PCo1, y = PCo2)) +
  geom_point(aes(shape = Habitat, color = Source)) +
  coord_fixed() +
  facet_grid(Habitat ~ Source) +
  scale_shape_manual(values = c(16, 17, 18))


# Nonmetric Multidimensional Scaling (NMDS)

# Recall that NMDS is more robust as it does not use metric distances, but it is more computationally intensive

# Create a function to conduct a nonmetric multidimensional scaling (NMDS) by running the metaMDS
#function (in the vegan package) multiple times for a range of possible values for k
# (the number of dimensions) and recording the stress values (a measure of goodness of fit; the lower the better):

nmds.stress <- function(x, sim = 100, kmax = 4) {
  sapply(seq_len(kmax), function(k)
    replicate(sim, metaMDS(x, k = k, autotransform = FALSE)$stress))
}

stress <- nmds.stress(distChickadee, sim = 100)

# Look at boxplots of the results as a diagnostic plot to choose k:
dfstr <- melt(stress, varnames = c("replicate","dimensions"))
ggplot(dfstr, aes(y = value, x = dimensions, group = dimensions)) +
  geom_boxplot()

# Compare the observed distances and their approximations using a Shepard plot for k = 2:
nmdsk2 <- metaMDS(distChickadee, k = 2, autotransform = FALSE)
stressplot(nmdsk2, pch = 20)

# Plot the different colors using the first two NMDS axes and notice the results are similar to the MDS solution in this case:

dfnmdsk2 <-
  data.frame(list(NmMDS1 = nmdsk2$points[,1],
                  NmMDS2 = nmdsk2$points[,2],
                  Site = rownames(distChickadee)
  ))

# Merge the two data frames by the common column
merged_df <- merge(dfnmdsk2, chickadeeData, by = "Site")

# Plotting by Habitat
ggplot(merged_df, aes(x = NmMDS1, y = NmMDS2)) +
  geom_point(aes(colour = Habitat), size = 4) +
  geom_text_repel(aes(label = Site), size = 3, max.overlaps = 100)

# Plotting by Source
ggplot(merged_df, aes(x = NmMDS1, y = NmMDS2)) +
  geom_point(aes(colour = Source), size = 4) +
  geom_text_repel(aes(label = Site), size = 3, max.overlaps = 100)


# Parallelizing Code

# Load the libraries
library(foreach)
library(doParallel)

# Register the parallel backend
registerDoParallel(cores = 4)  # Replace '4' with the number of available cores

# Define the NMDS function
nmds.stress <- function(x, sim = 100, kmax = 4) {
  foreach(k = seq_len(kmax), .combine = 'c', .packages = 'vegan') %dopar% {
    replicate(sim, metaMDS(x, k = k, autotransform = FALSE)$stress)
  }
}

# Run the NMDS function
stress <- nmds.stress(distChickadee, sim = 100)



# Correspondence Analysis (CA) for Contingency Tables: One Categorical Variable

# Consider the data on HIV mutations (the data consists of a mutation co-occurrence matrix):
cooc <- read.csv("CooccurHIV.csv", row.names=1)
cooc[1:6, 1:6]

# A chi-squared test of independence strongly suggests the co-occurrence of mutations is not independent:
chisq.test(cooc, correct = FALSE)

# X-squared = 724268, df = 22201, p-value < 2.2e-16

# Conduct a correspondence analysis (CA), also known as dual scaling, using the dudi.coa function (in the ade4 package):
HIVca<- dudi.coa(cooc, scannf = FALSE, nf = 4)
summary(HIVca)

fviz_eig(HIVca, geom = "bar", bar_width = 0.6) +
  ggtitle("")

# Plot the mutations in a lower dimensional projection, in this case three dimensions (“spin” the plot to see the structure):
CA1 <- HIVca$li[,1]
CA2 <- HIVca$li[,2]
CA3 <- HIVca$li[,3]
plot3d(CA1, CA2, CA3, aspect = FALSE, col = "purple")

# We can also consider static two-dimensional views by plotting two eigenvectors at a time:
  fviz_ca_row(HIVca,axes = c(1, 2), geom="text", col.row="purple",
              labelsize=3) +
  ggtitle("") +
  xlim(-0.55, 1.7) +
  ylim(-0.53, 1.1) +
  theme_bw() +
  coord_fixed() # first eigenvector versus second eigenvector

fviz_ca_row(HIVca,axes = c(1, 3), geom="text",col.row="purple",
            labelsize=3) +
  ggtitle("") +
  xlim(-0.55, 1.7) +
  ylim(-0.5, 0.6) +
  theme_bw() +
  coord_fixed() # first eigenvector versus third eigenvector


# CA for Contingency Tables: Two Categorical Variables
HairEyeColor <-
  array(c(36,66,16,4,9,34,7,64,5,29,7,5,2,14,7,8),
        dim=c(4,4),
        dimnames=list(Hair=c("Black","Brown","Red","Blond"),
                      Eye=c("Brown","Blue","Hazel","Green")))

HairEyeColor

# A chi-squared test of independence indicates hair-color and eye-color are not independent:
chisq.test(HairEyeColor)

# X-squared = 106.66, df = 9, p-value < 2.2e-16

# We can calculate expected frequencies under independence and confirm the chi-squared test statistic:
rowsums <- as.matrix(apply(HairEyeColor,1,sum))
colsums <- as.matrix(apply(HairEyeColor,2,sum))
HCexp <- rowsums%*%t(colsums)/sum(colsums)

HCexp

sum((HairEyeColor - HCexp)^2 / HCexp) # chi-squared statistic = 106.6637

# We can then study the residuals from the expected table numerically and visually in a mosaic plot:
round(t(HairEyeColor - HCexp)) # residuals from the expected table
mosaicplot(HairEyeColor, shade = TRUE, las = 1, type = "pearson",
           cex.axis = 0.7, main = "")

# Conduct a CA using the dudi.coa function:
  coaHC <- dudi.coa(HairEyeColor, scannf = FALSE, nf = 2)

summary(coaHC)

# Calculate the proportion of the chi-squared statistic explained by each axis of the CA
# by finding the ratio of each eigenvalue to the sum of the eigenvalues:
round(coaHC$eig[1:3]/sum(coaHC$eig), 2) # 0.89 0.10 0.02

# Create a biplot to visualize co-occurrence of the categories using the fviz_ca_biplot function:
fviz_ca_biplot(coaHC, repel=TRUE, col.col = "brown",
  col.row = "purple") +
  ggtitle("") +
  ylim(c(-0.5,0.5)) +
  coord_fixed()

# Alternatively, conduct a CA using the cca function (in the vegan package):
res.ca <- cca(HairEyeColor)
plot(res.ca, scaling = 3)
plot(c(-1, 1.5), c(-1, 1), type = "n", xlab = "CA1", ylab = "CA2",
xlim = c(-1, 1.5), ylim = c(-1, 1))
text(res.ca, display = "sites", scaling = 3, col = "black",
     cex = 0.8)
text(res.ca, display = "species", scaling = 3, col = "red",
     cex = 0.8)
