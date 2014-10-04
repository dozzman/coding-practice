let number_of_divisors num =
  let build_divisor_list start = 
    let rec build_divisor_list_helper orig curr = 
    match curr with
    | 1 -> [1]
    | _ -> if orig mod curr = 0 then curr::build_divisor_list_helper orig (curr - 1)
            else build_divisor_list_helper orig (curr - 1)
  in
    num::(build_divisor_list_helper num (num / 2))
in
  let div_list = build_divisor_list num in
    begin
(*      print_string "Integer "; print_int num; print_endline " has divisors...";*)
(*      List.iter (fun x -> begin print_int x; print_string ", " end) div_list;*)
(*      print_newline ();*)
      List.length (div_list)
    end
    

let rec tri_divisor_500 tri_index tri_num = 
  if number_of_divisors tri_num <= 500
  then
    tri_divisor_500 (tri_index + 1) (tri_num + tri_index + 1)
  else
    tri_num


let () = print_int (tri_divisor_500 2 3); print_newline ()
