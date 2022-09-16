#![allow(dead_code)]

use jni::JNIEnv;
use jni::sys::jfloatArray;

pub(super) fn jint_array_to_vec_i32(env: JNIEnv, array: jfloatArray) -> Vec<f32> {
    let mut ret: Vec<f32> = Vec::new();
    let length: usize = env.get_array_length(array).unwrap() as usize;

    ret.resize(length, 0.0);
    env.get_float_array_region(array, 0, &mut ret).unwrap();

    ret
}