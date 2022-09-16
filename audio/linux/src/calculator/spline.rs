use crate::calculator::polynomial::CubicFunction;
use crate::calculator::{Graph, ParseError};

pub(crate) struct Spline {
    val: Vec<(f32, f32, CubicFunction)>,
}

impl Graph for Spline {
    fn from_string(x: String) -> Option<Self> where Self: Sized {
        let mut ret = Spline { val: Vec::new() };

        let sp = x.split('#');
        for i in sp {
            let tmp = i.split_whitespace().collect::<Vec<&str>>();
            ret.val.push((tmp[0].parse().unwrap(), tmp[1].parse().unwrap(),
                          CubicFunction::from_string(tmp[2].to_string()).unwrap()));
        }

        Some(ret)
    }

    fn from_vec_f32(x: Vec<f32>) -> Result<Self, ParseError> {
        if x.len() % 6 != 0 {
            return Result::Err(ParseError)
        }

        let mut ret = Spline { val: Vec::new() };

        while !x.is_empty() {
            ret.val.push((x[0], x[1], CubicFunction::from_vec_f32(Vec::from(&x[0..6])).unwrap()))
        }

        Result::Err(ParseError)
    }

    fn value(&self, x: f32) -> Option<f32> {
        self.find(x).map(|i| self.val[i].2.value(x).unwrap())
    }
}

impl Spline {
    fn find(&self, x: f32) -> Option<usize> {
        if x < self.val[0].0 {
            return None;
        }
        for i in 0..self.val.len() {
            if x >= self.val[i].0 && x <= self.val[i].1 {
                return Some(i);
            }
        }
        None
    }
}
