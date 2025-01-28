fn main(){
	let mut f = [100, 2, 3, 1, 3,1 ,141];
	let slicing = &f[..];
	let s2 = &f[3..];
	let s3 = &f[..4];
        println!("{:?}\t{:?}\t{:?}\t{:?}", f, slicing, s2, s3);
        let s4 = &mut f[..3];
        println!("{:?}", &mut f);
        s4[2] = 9;
        println!("{:?}", &mut f);
}
