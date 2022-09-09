
use jni::sys::{jclass, jint, JNIEnv};

/*
 * Class:     com_draw_drawmusic_tools_Rust
 * Method:    x
 * Signature: (II)I
 */
#[no_mangle]
pub extern fn Java_com_draw_drawmusic_tools_Rust_x(_env: JNIEnv, _caller: jclass, a: jint, b: jint) -> jint {
    println!("called!");
    test_add(a, b)
}

fn test_add(a: i32, b: i32) -> i32 {
    a + b
}