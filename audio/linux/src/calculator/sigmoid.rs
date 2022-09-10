
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