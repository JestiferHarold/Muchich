.model small
.stack 100h

.data
    message db 'Daisy Girl$'   ; This is the message to display. The '$' sign is used to mark the end of the string for DOS functions.

.code
main:
    ; Initialize the data segment
    mov ax, @data
    mov ds, ax

    ; Print the message
    mov ah, 09h                ; DOS interrupt function for printing a string
    lea dx, message            ; Load the address of the message into DX
    int 21h                    ; Call DOS interrupt to print string

    ; Exit program
    mov ah, 4Ch                ; DOS function to terminate the program
    int 21h                    ; Call DOS interrupt to exit

end main


