
use crate::calculator::{Graph, ParseError};

pub(super) struct CubicFunction {
    pub(super) val: Vec<f32>,
}

impl Graph for CubicFunction {
    fn from_string(x: String) -> Option<Self> {
        let mut ret = CubicFunction { val: Vec::new() };

        let sp = x.split('$');
        for i in sp {
            match i.trim().parse::<f32>() {
                Err(_) => { return None; }
                Ok(coefficient) => {
                    ret.val.push(coefficient);
                }
            }
        }

        Some(ret)
    }

    fn from_vec_f32(x: Vec<f32>) -> Result<Self, ParseError> {
        if x.len() != 4 {
            return Result::Err(ParseError);
        }

        Result::Ok(CubicFunction {
            val: x
        })
    }

    fn value(&self, x: f32) -> Option<f32> {
        let mut ans = 0f32;

        for i in self.val.iter() {
            ans *= x;
            ans += i;
        }

        Some(ans)
    }
}
