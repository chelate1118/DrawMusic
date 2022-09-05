#![allow(unused)]

use num_bigint::BigInt;
use num_traits::{Num, Zero, One};

pub(crate) fn sum(command: String) {
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
    val: Vec<f64>,
}

impl Polynomial {
    pub(crate) fn from_string(x: String) -> Polynomial {
        let mut ret = Polynomial { val: Vec::new() };

        let sp = x.split('$');
        for i in sp {
            ret.val.push(i.parse::<f64>().unwrap());
        }

        ret
    }

    pub(crate) fn value(&self, x: f64) -> f64 {
        let mut ans = 0f64;

        for i in self.val.iter() {
            ans *= x;
            ans += i;
        }

        ans
    }
}

pub(crate) struct Spline {
    val: Vec<(f64, f64, Polynomial)>,
}

impl Spline {
    pub(crate) fn from_string(x: String) -> Spline {
        let mut ret = Spline { val: Vec::new() };

        let sp = x.split('#');
        for i in sp {
            let tmp = i.split_whitespace().collect::<Vec<&str>>();
            ret.val.push((tmp[0].parse().unwrap(), tmp[1].parse().unwrap(),
                          Polynomial::from_string(tmp[2].to_string())));
        }

        ret
    }

    fn find(&self, x: f64, st: usize) -> Option<usize> {
        if x < self.val[0].0 {
            return None;
        }
        for i in st..self.val.len() {
            if x >= self.val[i].0 && x <= self.val[i].1 {
                return Some(i);
            }
        }
        None
    }

    pub(crate) fn value(&self, x: f64, st: usize) -> Option<(f64, usize)> {
        match self.find(x, st) {
            Some(i) => Some((self.val[i].2.value(x), i)),
            None => None
        }
    }
}
