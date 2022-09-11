use jni::JNIEnv;
use jni::sys::{jintArray};

pub(super) fn jint_array_to_vec(env: JNIEnv, array: jintArray) -> Vec<i32> {
    let mut ret: Vec<i32> = Vec::new();
    let length: usize = env.get_array_length(array).unwrap() as usize;

    ret.resize(length, 0);
    env.get_int_array_region(array, 0, &mut ret).unwrap();

    ret
}