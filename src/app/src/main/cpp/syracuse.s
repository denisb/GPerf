
.section .text
.align  2
.global syracuse
.type   syracuse, %function

syracuse:
    @ADD R6, %1, #1
    MOV R7, #1
    MOV R8, R7
    B fin
change:ADD R7, #1
    MOV R8, R7
    B suite
boucle: CMP R8,#1
    BEQ change
    CMP R8,#-1
    BEQ change
    CMP R8,#-10
    BEQ change
    CMP R8,#-34
    BEQ change
suite:ANDS R9, R8, #1
    BEQ div
    ADD R8, R8, R8,LSL #1
    ADD R8, R8, #1
div:MOV R8, R8, ASR #1
fin:SUBS R6,#1
    BNE boucle
    @MOV %0, R7
    bx  lr
    .size   syracuse, .-syracuse