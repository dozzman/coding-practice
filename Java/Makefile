JAVAC=javac
java=java

all: sorting

sorting: sorting/Sorter.class sorters sorting/SortTester.class

sorters: sorting/BubbleSort.class

clean:
	rm sorting/*.class

runsort: sorting
	java sorting/SortTester

%.class: %.java
	javac $<

.PHONY: all sorting
