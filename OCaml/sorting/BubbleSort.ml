let rec bubble_sort = function
  | x1::x2::xs when x1 > x2 -> begin
    match bubble_sort (x1::xs) with
    | None -> Some (x2::x1::xs)
    | Some xs2 -> Some (x1::xs2)
    end

  | x1::x2::xs -> begin
    match bubble_sort (x2::xs) with
    | None -> None
    | Some xs2 -> Some (x1::xs2)
    end

  | _ -> None

let rec rec_sort l =
  let l2 = (bubble_sort l) in
    match l2 with
    | None -> l
    | Some l3 -> rec_sort l3

let name = "Bubble Sort"
