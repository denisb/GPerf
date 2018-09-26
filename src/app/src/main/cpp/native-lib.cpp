#include <jni.h>
#include <string>


extern "C" JNIEXPORT jstring
JNICALL
Java_edu_bdenis_gperf_MainActivity_stringFromJNICalcReg(
        JNIEnv *env,
        jobject /* this */,
        int pTailleDonnees) {
    int i,n,r;
    n=1;
    r=n;
    char str[20]; // asm ou __asm__ ou __asm // et volatile ?
    __asm(
    "ADD R6, %1, #1;"
    "MOV R7, #1;"
    "MOV R8, R7;"
    "B fin;"
"change:ADD R7, #1;"
    "MOV R8, R7;"
    "B suite;"
"boucle: CMP R8,#1;"
    "BEQ change;"
    "CMP R8,#-1;"
    "BEQ change;"
    "CMP R8,#-10;"
    "BEQ change;"
    "CMP R8,#-34;"
    "BEQ change;"
"suite:ANDS R9, R8, #1;"
    "BEQ div;"
    "ADD R8, R8, R8,LSL #1;"
    "ADD R8, R8, #1;"
"div:MOV R8, R8, ASR #1;"
"fin:SUBS R6,#1;"
    "BNE boucle;"
    "MOV %0, R7;"
    : "=r" (n)
    : "r" (pTailleDonnees)
    :
    );

    std::string hello = "Calcul fini avec ";
    sprintf(str,"%d",n);
    hello += str;
    hello += ". ";
    return env->NewStringUTF(hello.c_str());
}



extern "C" JNIEXPORT jstring
JNICALL
Java_edu_bdenis_gperf_MainActivity_stringFromJNICalcVarOO(
        JNIEnv *env,
        jobject /* this */,
        int pTailleDonnees) {
    int n;
    int i, r;
    n=1;
    r=n;
    char str[20];
    __asm(
    "ADD R6, %1, #1;" //i
    "CMP R6, #0;"
    "BEQ .L2;"
    "MOV R7, #1;" //n
".L7:CMN R8, #34;" //r
    "IT ne;"
    "CMPNE R8, #1;"
    "BEQ .L3;"
    "CMN R8, #1;"
    "IT ne;"
    "CMNNE R8, #10;"
    "BNE .L4;"
".L3:ADD R7, R7, #1;"
    "MOV R8, R7;"
".L4:TST R8, #1;"
    "ITT ne;"
    "ADDNE R8, R8, R8, asl #1;"
    "ADDNE R8, R8, #1;"
    "ADD R8, R8, R8, lsr #31;"
    "SUBS R6, R6, #1;"
    "MOV R8, R8, asr #1;"
    "BNE .L7;"
".L2:MOV R6, R8;"
    "MOV %0, R7;"
    : "=r" (n)
    : "r" (pTailleDonnees)
    :
    );

    std::string hello = "Calcul fini avec ";
    sprintf(str,"%d",n);
    hello += str;
    hello += ". ";
    return env->NewStringUTF(hello.c_str());
}


extern "C" JNIEXPORT jstring
JNICALL
Java_edu_bdenis_gperf_MainActivity_stringFromJNICalcVar(
        JNIEnv *env,
        jobject /* this */,
        int pTailleDonnees) {
    int i,n,r;
    n=1;
    r=n;
    char str[20];

    for(i=pTailleDonnees;i;i--) {
        if ((r==1) || (r==-34) || (r==-10) || (r==-1)) {n=n+1; r = n;}
        if (r&1) {r=(r*3+1)/2;}
        else {r=r/2;}}
    std::string hello = "Calcul fini avec ";
    sprintf(str,"%d",n);
    hello += str;
    hello += ". ";
    return env->NewStringUTF(hello.c_str());
}
