SORTING_SOURCES+=$(wildcard sorting/*.cc)
SORTING_OBJECTS=$(SORTING_SOURCES:.cc=.o)
GTEST_DIR=gtest-1.7.0

CXXFLAGS+=-Wall -std=c++11 -isystem $(GTEST_DIR)/include

all: SortTest

SortTest: libgtest.a $(SORTING_OBJECTS)
	$(CXX) -o SortTest $(CXXFLAGS) $+

libgtest.a: gtest-all.o
	ar -r libgtest.a gtest-all.o

gtest-all.o: $(GTEST_DIR)/src/gtest-all.cc
	$(CXX) -isystem $(GTEST_DIR)/include -I$(GTEST_DIR) -pthread -c $<

runsort: SortTest
	./SortTest

clean:
	-find . -iname "*.[oa]" -exec $(RM) {} +
	-$(RM) SortTest


.PHONY: all sorting runsort clean
