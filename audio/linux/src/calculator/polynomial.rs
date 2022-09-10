
use crate::calculator::Graph;

pub(super) struct Polynomial {
    pub(super) val: Vec<f64>,
}

impl Graph for Polynomial {
    fn from_string(x: String) -> Option<Self> {
        let mut ret = Polynomial { val: Vec::new() };

        let sp = x.split('$');
        for i in sp {
            match i.trim().parse::<f64>() {
                Err(_) => { return None; }
                Ok(coefficient) => {
                    ret.val.push(coefficient);
                }
            }
        }

        Some(ret)
    }

    fn value(&self, x: f64) -> Option<f64> {
        let mut ans = 0f64;

        for i in self.val.iter() {
            ans *= x;
            ans += i;
        }

        Some(ans)
    }
}
