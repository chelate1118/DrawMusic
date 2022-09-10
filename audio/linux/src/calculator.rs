#![allow(unused)]

mod sigmoid;
mod polynomial;
mod spline;
use assert_approx_eq::assert_approx_eq;
use polynomial::Polynomial;

pub(crate) trait Graph {
    fn from_string(x: String) -> Option<Self> where Self: Sized;
    fn value(&self, x: f64) -> Option<f64>;
}

/// Test Code

#[test]
fn test_sigmoid_value()
{
    assert_approx_eq!(0.5f64, sigmoid::value(0f64));
    assert_approx_eq!(1.0f64, sigmoid::value(f64::MAX));
    assert_approx_eq!(0.0f64, sigmoid::value(f64::MIN));
    assert_approx_eq!(0.880797077977882444059729141302396795, sigmoid::value(2f64));
    assert_approx_eq!(0.047425873177566780878848151771752201, sigmoid::value(-3f64));
}

#[test]
fn test_sigmoid_inverse()
{
    assert_approx_eq!(0f64, sigmoid::inverse(0.5f64).unwrap());
    assert_eq!(None, sigmoid::inverse(1.0f64));
    assert_eq!(None, sigmoid::inverse(0.0f64));
    assert_eq!(None, sigmoid::inverse(-0.5f64));
    assert_approx_eq!(2f64,  sigmoid::inverse(0.880797077977882444059729141302396795).unwrap());
    assert_approx_eq!(-3f64, sigmoid::inverse(0.047425873177566780878848151771752201).unwrap());
}

#[test]
fn test_polynomial_from_string()
{
    let command = String::from("\n0.46$3.2 $1.0\n");
    let polynomial = Polynomial::from_string(command).unwrap();

    assert_eq!(polynomial.val, vec![0.46, 3.2, 1.0f64]);
}

#[test]
fn test_polynomial_value()
{
    let polynomial = Polynomial{ val : vec![140.0, 0.0, -90.7, 3.78] };

    assert_eq!(Some(3.78f64), polynomial.value(0f64));
    assert_eq!(Some(53.08f64), polynomial.value(1f64));
}

#[test]
fn test_spline_from_string()
{
    // TODO : Make Spline string format
}
