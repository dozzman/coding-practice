module type SortType = sig
  val rec_sort : 'a list -> 'a list
  val name : string
end

(* pretty useless, but I may expand this sorter class to provide more *)
module Sorter ( SorterModule : SortType ) = struct
  include SorterModule
end

module CurrentSorter = Sorter ( BubbleSort )

let debugging = true

let rec randlist = function
| 0 -> []
| x -> (Random.int ((1 lsl 20)))::(randlist (x - 1))

let perform_sort l = 
begin
  print_endline "Performing sort now...";
  if debugging then
  begin
    print_endline "List of integers...";
    List.iter (fun x -> print_int x; print_newline ()) l;
    let new_l = CurrentSorter.rec_sort l in
    begin
      print_endline "Sorted list of integers...";
      List.iter (fun x -> print_int x; print_newline ()) new_l;
    end
  end;
  CurrentSorter.rec_sort l
end

let check_sort l =
  let rec check_sort_helper = function
  | x1::x2::xs when x1 <= x2 -> check_sort_helper (x2::xs)
  | x1::x2::xs when x1 > x2 -> false
  | _ -> true
  in
  begin
    print_endline "Checking sort validity...";
    if (check_sort_helper l) then print_endline "Sort was a success!"
    else print_endline "Sort failed!"
  end

let () =
let l = randlist 10 in
begin
  Random.self_init ();
  List.iter print_string ["Test harness will sort with "; CurrentSorter.name];
  print_newline ();
  check_sort (perform_sort l)
end
