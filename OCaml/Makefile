OCAMLOPT=ocamlopt.opt
OCAMLC=ocamlc.opt
CMIOPTIONS=-c
CMOOPTIONS=-c
CMXOPTIONS=-c

all: SortTest BSTree.cmo

SortTest: BubbleSort.cmx BSTree.cmx SortTest.cmx
	$(OCAMLOPT) -o $@ $+

SortTest.cmx: SortTest.cmi

BubbleSort.cmx: BubbleSort.cmi

%.cmx: %.ml
	$(OCAMLOPT) -o $@ $(CMXOPTIONS) $<

%.cmi: %.mli
	$(OCAMLOPT) -o $@ $(CMIOPTIONS) $<

%.cmo: %.ml
	$(OCAMLC) -o $@ $(CMOOPTIONS) $<
clean:
	-rm *.cmi *.cmx *.o
	-rm SortTest

.PHONY: all clean
