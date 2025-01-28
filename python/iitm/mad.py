from sklearn.datasets import load_iris
import pandas

iris = load_iris()
data = pandas.DataFrame(iris.data, column = iris.feature_name)
data['target'] = iris.target

print(data.head())
