use std::io;

fn main(){
    let answer : String = String::from("I love you mf");
    print!("{}\n", answer.chars().nth(0).unwrap());
    
    let mut a = String::new();
    io::stdin().read_line(&mut a).unwrap();
    print!("{}",a);
}
