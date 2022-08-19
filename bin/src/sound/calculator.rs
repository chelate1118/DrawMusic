#![allow(unused)]

use num_bigint::BigInt;
use num_traits::{Num, Zero, One};

pub(crate) fn sum(command : String) {
    let mut ans: BigInt = BigInt::zero();
    for i in command.split_whitespace() {
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

pub(crate) fn multiple(command: String) {
    let mut ans: BigInt = BigInt::one();
    for i in command.split_whitespace() {
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

pub(crate) struct Polynomial {
    dim : usize,
    val : Vec<f64>
}

impl Polynomial {
    /*pub(crate) fn new(command : String) -> Polynomial {
        let x = command.split_whitespace();
        let x = x.collect::<Vec<String>>();
        let mut ret = Polynomial {
            dim : x[0].parse().unwrap(),
            val : Vec::new()
        };
        assert_eq!(ret.dim, x.len() - 1);
        for i in 0..ret.dim {
            ret.val.push(x[i + 1].parse().unwrap());
        }
        ret
    }*/

    pub(crate) fn to_string(&self) -> String {
        let mut ans = self.dim.to_string();

        for i in 0..self.dim {
            ans.push(' ');
            ans.push_str(&self.val[i].to_string());
        }

        ans
    }

    pub(crate) fn value(&self, x : f64) -> f64 {
        let mut ans = 0f64;

        for i in 0..self.dim {
            ans *= x;
            ans += self.val[i];
        }

        ans
    }
}