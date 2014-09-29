SORTING_SOURCES=$(wildcard sorting/*.cc)
SORTING_OBJECTS=$(SORTING_SOURCES:.cc=.o)
SORTING_DEPS=$(SORTING_OBJECTS:.o=.d)
GTEST_DIR=gtest-1.7.0

CXXFLAGS+=-Wall -std=c++11 -isystem $(GTEST_DIR)/include

all: SortTest

-include $(SORTING_DEPS)

SortTest: libgtest.a $(SORTING_OBJECTS)
	$(CXX) -o SortTest $(LDFLAGS) $+

libgtest.a: gtest-all.o
	ar -r libgtest.a gtest-all.o

gtest-all.o: $(GTEST_DIR)/src/gtest-all.cc
	$(CXX) -isystem $(GTEST_DIR)/include -I$(GTEST_DIR) -pthread -c $<

%.o: %.cc
	$(CXX) -c $(CXXFLAGS) -o $@ $<
	$(CXX) -MM -MT $@ $(CXXFLAGS) -o $*.d $<

runsort: SortTest
	./SortTest

clean:
	-$(RM) sorting/*.o *.d
	-$(RM) SortTest


.PHONY: all sorting runsort clean
