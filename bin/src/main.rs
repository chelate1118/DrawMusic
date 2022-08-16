
use num_bigint::BigInt;
use num_traits::{Num, Zero, One};

fn input() -> String {
    let mut tmp : String = String::new();
    std::io::stdin().read_line(&mut tmp).ok();

    tmp
}

fn sum() {
    let mut ans: BigInt = BigInt::zero();
    for i in input().split_whitespace() {
        match BigInt::from_str_radix(i, 10) {
            Ok(val) => { ans += val; }
            Err(_) => {
                println!("Error");
                return;
            }
        }
    }
    println!("{}", ans.to_string());
}

fn multiple() {
    let mut ans: BigInt = BigInt::one();
    for i in input().split_whitespace() {
        match BigInt::from_str_radix(i, 10) {
            Ok(val) => { ans *= val; }
            Err(_) => {
                println!("Error");
                break;
            }
        }
    }
    println!("{}", ans.to_string());
}

fn main() {
    loop {
        match input().trim() {
            "c s" => { sum() }
            "c m" => { multiple() }
            "t" => { break; }
            _ => { println!("error") }
        }
    }
}
