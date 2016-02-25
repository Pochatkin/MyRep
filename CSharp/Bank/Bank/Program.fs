let scalar(amount: list<int>) (ratigs: list<int>) = 
    if amount.Length = ratigs.Length 
    then
        List.fold2(fun acc x y -> acc + x * y) 1 amount ratigs
    else 
        0


[<EntryPoint>]
let main args = 
    let a = System.Console.ReadLine()
            
    0


