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
    

let rec tri_divisor_500_with_func divisor_func tri_index tri_num = 
  if divisor_func tri_num <= 500
  then
    tri_divisor_500_with_func divisor_func (tri_index + 1) (tri_num + tri_index + 1)
  else
    tri_num

let prime_list = ref [2]

let rec int_range x y = if x <= y then x::int_range (x+1) y else []

let is_prime x =
  let rec is_prime_helper orig curr = match curr with
  | x when x < 1 -> false
  | 1 -> true
  | _ ->  if orig mod curr = 0 then false
    else is_prime_helper orig (curr - 1)
  in
    match x with
    | 1 -> false
    | 2 | 3 -> true
    | _ -> is_prime_helper x (int_of_float (sqrt ( float_of_int x ) ) )

let update_prime_list x =
  let head = List.hd !prime_list in
    let upper_limit = (int_of_float (sqrt ( float_of_int x ) ) ) in
    if head >= upper_limit then
      prime_list := x::!prime_list
    else
      List.iter (fun p -> if is_prime p then prime_list := p::(!prime_list))  ( int_range head upper_limit )
      
let rec prime_factors x = 
  let single_primes y =
    let primes = (List.filter ( fun p -> y mod p = 0 ) !prime_list) in
      if primes = [] then
        update_prime_list x;
      primes
  in
    match x with
    | 1 -> []
    | _ ->
      let primes = single_primes x in
      let new_x = List.fold_left ( fun x y -> x / y ) x (primes) in
        primes@(prime_factors new_x)

let rec count_unique_factors x = match x with
| [] -> []
| _ ->
  let head = List.hd x in
    let (yes_list, no_list) = List.partition ( fun x -> x = head ) x in
      (List.length yes_list)::(count_unique_factors no_list)

let number_of_divisors2 x =
  let factors = prime_factors x in
    let factor_counts = count_unique_factors factors in
      List.fold_left (fun x y -> (x)*(y+1)) 1 factor_counts

let tri_divisor_500 = tri_divisor_500_with_func number_of_divisors

let tri_divisor_500_2 = tri_divisor_500_with_func number_of_divisors2

(*let () = print_int (tri_divisor_500 2 3); print_newline ()*)

let () = print_int (tri_divisor_500_2 2 3); print_newline ()
