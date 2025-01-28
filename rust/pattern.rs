fn main(){
    enum Planet{
        MERCURY,
        VENUS,
        EARTH,
        MARS,
        JUPITER,
        SATURN,
        URANUS,
        PLUTO
    }

    let mut m : Planet = Planet::PLUTO;
    
    match m{

        Planet::MERCURY => print!("The planet is mercury"),
        Planet::VENUS => print!("The planet is venus"),
        Planet::EARTH => print!("The planet is earth"),
        Planet::MARS => print!("The planet is mars"),
        Planet::JUPITER => print!("The planet is jupiter"),
        Planet::SATURN => print!("The planet is Saturn"),
        Planet::URANUS => print!("The planet is uranus"),
        Planet::PLUTO => print!("The planet is pluto"),
        _ => print!("NO IDEA BRO"),

    }

    let x = 100;
    match x{
        1 => print!("X is 1"),
        2 => print!("X is 2"),
        4 => print!("X is 5"),
        _ => print!("X is neither of these bitches"),
    }
}
