
use crate::calculator::polynomial::Polynomial;
use crate::calculator::Graph;

pub(crate) struct Spline {
    val: Vec<(f64, f64, Polynomial)>,
}

impl Graph for Spline {
    fn from_string(x: String) -> Option<Self> where Self: Sized {
        let mut ret = Spline { val: Vec::new() };

        let sp = x.split('#');
        for i in sp {
            let tmp = i.split_whitespace().collect::<Vec<&str>>();
            ret.val.push((tmp[0].parse().unwrap(), tmp[1].parse().unwrap(),
                          Polynomial::from_string(tmp[2].to_string()).unwrap()));
        }

        Some(ret)
    }

    fn value(&self, x: f64) -> Option<f64> {
        match self.find(x) {
            Some(i) => Some(self.val[i].2.value(x).unwrap()),
            None => None
        }
    }
}

impl Spline {
    fn find(&self, x: f64) -> Option<usize> {
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