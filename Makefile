all: java ocaml cpp

java:
	$(MAKE) -C Java

ocaml:
	$(MAKE) -C OCaml

cpp:
	$(MAKE) -C C++

clean:
	$(MAKE) -C Java clean
	$(MAKE) -C OCaml clean
	$(MAKE) -C C++ clean

.PHONY: java ocaml clean cpp
