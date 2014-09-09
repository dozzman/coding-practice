all: java ocaml

java:
	$(MAKE) -C Java

ocaml:
	$(MAKE) -C OCaml

clean:
	$(MAKE) -C Java clean
	$(MAKE) -C OCaml clean

.PHONY: java ocaml clean
