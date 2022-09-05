#![allow(unused)]

pub(crate) struct Sigmoid {}

impl Sigmoid {
    pub(crate) fn value(x: f64) -> f64 {
        1.0f64 / (1.0f64 + (-x).exp())
    }

    pub(crate) fn inverse(y: f64) -> Option<f64> {
        if y >= 1f64 || y <= 0f64 {
            None
        } else {
            Some((y / (1.0 - y)).ln())
        }
    }
}

pub(crate) struct Polynomial {
    val: Vec<f64>,
}

impl Polynomial {
    pub(crate) fn from_string(x: String) -> Polynomial {
        let mut ret = Polynomial { val: Vec::new() };

        let sp = x.split('$');
        for i in sp {
            ret.val.push(i.trim().parse::<f64>().unwrap());
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

use assert_approx_eq::assert_approx_eq;

#[test]
fn test_sigmoid_value()
{
    assert_approx_eq!(0.5f64, Sigmoid::value(0f64));
    assert_approx_eq!(1.0f64, Sigmoid::value(f64::MAX));
    assert_approx_eq!(0.0f64, Sigmoid::value(f64::MIN));
    assert_approx_eq!(0.880797077977882444059729141302396795, Sigmoid::value(2f64));
    assert_approx_eq!(0.047425873177566780878848151771752201, Sigmoid::value(-3f64));
}

#[test]
fn test_sigmoid_inverse()
{
    assert_approx_eq!(0f64, Sigmoid::inverse(0.5f64).unwrap());
    assert_eq!(None, Sigmoid::inverse(1.0f64));
    assert_eq!(None, Sigmoid::inverse(0.0f64));
    assert_eq!(None, Sigmoid::inverse(-0.5f64));
    assert_approx_eq!(2f64,  Sigmoid::inverse(0.880797077977882444059729141302396795).unwrap());
    assert_approx_eq!(-3f64, Sigmoid::inverse(0.047425873177566780878848151771752201).unwrap());
}

#[test]
fn test_polynomial_from_string()
{
    let command = String::from("\n0.46$3.2 $1.0\n");
    let polynomial = Polynomial::from_string(command);

    assert_eq!(polynomial.val, vec![0.46, 3.2, 1.0f64]);
}

#[test]
fn test_polynomial_value()
{
    let polynomial = Polynomial{ val : vec![140.0, 0.0, -90.7, 3.78] };

    assert_eq!(3.78f64, polynomial.value(0f64));
    assert_eq!(53.08f64, polynomial.value(1f64));
}

#[test]
fn test_spline_from_string()
{
    let spline = Spline::from_string(String::from(""));
    // TODO : Make Spline string format
}
