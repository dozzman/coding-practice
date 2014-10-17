let int_of_ascii x = x - 48

let () = 
  let x = Big_int.shift_left_big_int Big_int.unit_big_int 1000 in
    let x_string = Big_int.string_of_big_int x in
      let total = ref 0 in
      begin
        print_endline x_string;
        String.iter (fun c -> total := !total + (int_of_ascii (int_of_char c)) ) x_string;
        print_int (!total); print_newline ();
      end
