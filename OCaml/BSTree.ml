exception Deleting_error

type 'a tree =
| Br of 'a  * 'a tree * 'a tree
| Lf

let bound = ref 1000

let rec insert t n = match t with
| Lf -> Br (n, Lf, Lf)
| Br (a, l, r) -> if n <= a then Br (a, insert l n, r) else Br (a, l, insert r n)

let rec minimum t = match t with
| Br (_, (Br (_,_,_) as l), _) -> minimum l
| Br (a, Lf, _) -> Some a
| Lf -> None

let rec delete_successor t n = match t with
| Lf -> Lf
| Br (a, l, r) when n == a -> 
  let rec delete_minimum t1 = match t1 with
  | Lf -> Lf
  | Br (a1, (Br (_,_,_) as l1), r1) -> Br (a1, delete_minimum l1, r1)
  | Br (_, Lf, Lf) -> Lf
  | Br (_, Lf, Br (ra,rl,rr)) -> Br (ra, rl, rr) 
  in
  begin
    Br (a, l, delete_minimum r)
  end
| Br (a, l, r) -> if n < a then Br (a, delete_successor l n, r) else Br (a, l, delete_successor r n)


let rec successor t n = match t with
| Lf -> None
| Br (a, l, r) when n == a -> minimum r 
| Br (a, l, r) -> if n < a then successor l n else successor r n


let rec delete t n = match t with
| Lf -> Lf
| Br (a, l, r) as t1 when n == a ->
  let succ = successor t1 n in
    let t2 = delete_successor t1 n in
    begin
      match t2 with
        | Lf -> assert false (* this should never occur *)
        | Br (a1, l1, r1) ->
          if a != a1 then assert false (* shouldn't delete itself as it is not its own successor *)
          else match succ with
          | None -> Lf
          | Some x -> Br (x, l1, r1)
    end
| Br (a, l, r) -> if n < a then Br (a, delete l n, r)  else Br (a, l, delete r n)

let height_to_nodes x = if x < 30 then (1 lsl x) - 1 else 1

let random_height = function
| x when x <= 0 -> Lf
| x ->
    let rec random_bound_helper h l u = 
    let rand = l + Random.int (u - l) in
      match h with
      | 0 -> Lf
      | h -> Br (rand, random_bound_helper (h - 1) l (rand), random_bound_helper (h - 1) (rand+1) u) (* rand+1 is due to fact Random.int is inclusive of lower bound (0) and exclusive of upper bound *)
      in
        random_bound_helper x 0 !bound

let random_nodes x =
  let rec random_nodes_helper t n = match n with
  | 0 -> t
  | n -> random_nodes_helper (insert t (Random.int !bound)) (n - 1)
  in
    random_nodes_helper Lf x

