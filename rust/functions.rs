fn hello() {
    print!("Daisy Girl");
}

fn main() {
    let mut f = hello;
    f();
    subtract(10, 100);
    println!("{}",add(10, 100));
}

fn subtract(a : i32, b :i32) {
    let sum : i32 = a - b;
    println!("The sum is {}", sum); 
}

fn add(a : i32, b : i32) -> i32 {
    return a + b;
}
