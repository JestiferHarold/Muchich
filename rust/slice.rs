fn main() {
    let numbers = [1 , 3, 4, 6, 7];
    let slice = &numbers[1..3];
    let slice2 = &numbers[..3];
    let slice3 = &numbers[1..];
    println!("{:?} {:?} {:?} {:?}", numbers, slice, slice2, slice3);
    let mut f : [i32 ; 5] = [1 , 2, 3, 4, 5];
    let fs = &mut f[..];
    println!("{:?}", fs);
    fs[3] = 1;
    println!("{:?}", fs);
}                                          
