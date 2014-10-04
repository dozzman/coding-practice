let triangle x = x * (x + 1) / 2

let count_factors x =
  let rec count_factors_helper orig curr = match curr with
  | 1 -> 1
  | _ -> if orig mod curr = 0 then 2 + ( count_factors_helper orig (curr - 1) )
    else count_factors_helper orig (curr - 1)
  in
    count_factors_helper x (int_of_float (sqrt (float_of_int x ) ) )

let rec keep_counting x =
  begin
  if (count_factors (triangle x)) <= 500 then keep_counting (x+1)
  else triangle x
  end

let () = print_int (keep_counting 1); print_newline ()
