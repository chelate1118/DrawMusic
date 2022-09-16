#![allow(unused)]

mod sigmoid;
mod polynomial;
mod spline;
use assert_approx_eq::assert_approx_eq;
use polynomial::CubicFunction;

#[derive(Debug, Clone)]
pub struct ParseError;

pub(crate) trait Graph {
    fn from_string(x: String) -> Option<Self> where Self: Sized;
    fn from_vec_f32(x: Vec<f32>) -> Result<Self, ParseError> where Self: Sized;
    fn value(&self, x: f32) -> Option<f32>;
}

/// Test Code

#[test]
fn test_sigmoid_value()
{
    assert_approx_eq!(0.5f64, sigmoid::value(0f64));
    assert_approx_eq!(1.0f64, sigmoid::value(f64::MAX));
    assert_approx_eq!(0.0f64, sigmoid::value(f64::MIN));
    assert_approx_eq!(0.880_797_077_977_882_4, sigmoid::value(2f64));
    assert_approx_eq!(0.047_425_873_177_566_78, sigmoid::value(-3f64));
}

#[test]
fn test_sigmoid_inverse()
{
    assert_approx_eq!(0f64, sigmoid::inverse(0.5f64).unwrap());
    assert_eq!(None, sigmoid::inverse(1.0f64));
    assert_eq!(None, sigmoid::inverse(0.0f64));
    assert_eq!(None, sigmoid::inverse(-0.5f64));
    assert_approx_eq!(2f64,  sigmoid::inverse(0.880_797_077_977_882_4).unwrap());
    assert_approx_eq!(-3f64, sigmoid::inverse(0.047_425_873_177_566_78).unwrap());
}

#[test]
fn test_polynomial_from_string()
{
    let command = String::from("\n0.46$3.2 $1.0\n");
    let polynomial = CubicFunction::from_string(command).unwrap();

    assert_eq!(polynomial.val, vec![0.46, 3.2, 1.0f32]);
}

#[test]
fn test_polynomial_value()
{
    let polynomial = CubicFunction { val : vec![140.0, 0.0, -90.7, 3.78] };

    assert_eq!(Some(3.78f32), polynomial.value(0f32));
    assert_eq!(Some(53.08f32), polynomial.value(1f32));
}

#[test]
fn test_spline_from_string()
{
    // TODO : Make Spline string format
}
