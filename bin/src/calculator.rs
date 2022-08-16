use num_bigint::BigInt;
use num_traits::{Num, Zero, One};

pub(crate) fn sum(input : String) {
    let mut ans: BigInt = BigInt::zero();
    for i in input.split_whitespace() {
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

pub(crate) fn multiple(input : String) {
    let mut ans: BigInt = BigInt::one();
    for i in input.split_whitespace() {
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