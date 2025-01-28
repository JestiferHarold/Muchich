use std::any::type_name;

fn type_of<T>(_: T) -> & 'static str {
    type_name::<T>()
}

fn main(){
    let new : [u32 ; 4] = [ 3; 4];
    let numbers : [u32 ; 3] = [ 1, 2, 3];
    let f = [10,123,514123];
    println!("{:?}", f);
    for i in 0..4 {
        println!("{}", new[i])
    }
    println!("The number of arrays {:?}", numbers);
    println!("The second array {:?}", new);
    let mut array1 : [ i32; 6] = [10; 6];
    array1[2] = 3;
    println!("{:?}", array1);
//    for i in 0..6 {
  //      array1[i] = i;
   // }
    println!("{:?}", array1);
    let m = [1, 32, 2, 4];
    let slicing = &m[0..4];
    print!("{:?}", slicing);
}
