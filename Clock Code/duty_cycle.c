//*****************************************************************************
//
// Name:        Module 2b - Timer/Counter Basics
// Filename:    duty_cycle.c
// Author(s):   Daniel Peterson
//             
//
//
// Description: Configures ports and interrupt. Timer/Counter starts in CTC
//              mode. When compare interrupt occurs, the counter resets and
//              begins counting again. Pin 5 of PORTD is used to as a 
//              toggle bit in order to represent the output for the counter.
//              The interrupt will pulse Pin 5 with a user determined duty 
//              cycle and at the frequency specified by the user. The frequency 
//              can be changed when the user clears the previous value and the 
//              new frequency is in place after the user presses ENTER.
//4MHZ @1 OCR
//31.25KHZ @255 OCR
//
//*****************************************************************************

#include <intrinsics.h> 
#include <avr_macros.h>
#include <iom128.h>
#include <stdio.h>

#include "lcd.h"  

#pragma vector = TIMER1_COMPA_vect
__interrupt void timer_1_compare(void)
{

  TCCR1B = (0<<CS10) | (0<<CS11) | (0<<CS12);
  //PORTD = (1<<PD4) | (1<<PD5);
  PORTD = PORTD ^ 0x30;
 // __delay_cycles(16000);                //1/16MHZ * delay == 1000us or 1ms
  TCCR1B = (0<<CS10) | (0<<CS11) | (1<<CS12);               //001 from 111 64 prescaler
  //PORTE = (1<<PE0);
  //TCNT1 = 0;
}

#pragma vector = TIMER1_COMPB_vect
__interrupt void timer_1_compareb(void)
{

  TCCR1B = (0<<CS10) | (0<<CS11) | (0<<CS12);
  //PORTD = (1<<PD4) | (1<<PD5);
  PORTD = PORTD ^ 0x30;
 // __delay_cycles(16000);                //1/16MHZ * delay == 1000us or 1ms
  TCCR1B = (0<<CS10) | (0<<CS11) | (1<<CS12);               //001 from 111 64 prescaler
 // PORTE = (1<<PE0);
  TCNT1 = 0;
}


extern unsigned char temp = 0;
extern unsigned char temp2 = 0;

unsigned int dig1 = 0;

unsigned int dig2 = 0;

unsigned int dig3 = 0;

unsigned int dig4 = 0;

unsigned int keyval = 0;

unsigned int dig1a = 0;

unsigned int dig2a = 0;

char keycode;

int main(void)
{
  
    DDRD = 0x30;
    
    DDRE = 0x01;
    
    TCNT1 = 0;
    
    //OCR1A = 156;
    
    TIMSK = (1 << TOIE0) | (1 << TOIE1) | (1<<OCIE1A) | (1<<OCIE1B);
    
    TCCR1A = (0<<CS10) | (0<<CS11) | (0<<CS12) | (0<<WGM11);
    
    TCCR1B = (0<<CS10) | (0<<CS11) | (0<<CS12) | (1<<WGM12);
    
    CLEARBIT(PORTD, 4);
    
    CLEARBIT(PORTD, 5);
    
    PORTD = 0x30;
    
    PORTE = (0<<PE0);
    
   // PORTE = (1<<PE0);
    
    temp = PORTE;
    
    
    DDRD = 0xFE;                    // INT0 input
    PORTD = 0x01;               // INT0 pullup enabled
    
    // Configure PortC for keypad, initial configuration
    DDRC = 0xF0;        // High nibble outputs, low nibble inputs 
    PORTC = 0x0F;
    
    // Configure PortB for DOG LCD module's SPI interface
    DDRB = 0xFF;             // Set PortB to outputs
    SETBIT (PORTB, 0);       // unassert slave select
      
    MCUCR = 0x30;     // Sleep enabled for power down mode.
    EIMSK = 0x01;     // Enable interrupt INT0. 
                      // Low level by default (EICRA not written)
    init_lcd_dog();               // Call to asm subroutine init_dsp()

    clear_dsp();        // Call to C function clear_dsp()
    update_lcd_dog();   // Call to C function update_lcd_dog()
    
    __enable_interrupt();
    printf("FREQ KHZ = ");
    __delay_cycles(1000000);
    while(temp == 0)
    {
      temp = PORTE;
      if(keycode == 0)
        {
          putchar('0');
          update_lcd_dog();
        }
      else if(keycode == 1)
        {
          putchar('1');
          update_lcd_dog();
        }
      else if(keycode == 2)
        {
          printf("2");
          update_lcd_dog();
        }
      else if(keycode == 3)
        {
          printf("3");
          update_lcd_dog();
        }
      else if(keycode == 4)
        {
          printf("4");
          update_lcd_dog();
        }
      else if(keycode == 5)
        {
          printf("5");
          update_lcd_dog();
        }
      else if(keycode == 6)
        {
          printf("6");
          update_lcd_dog();
        }
      else if(keycode == 7)
        {
          printf("7");
          update_lcd_dog();
        }
      else if(keycode == 8)
        {
          printf("8");
          update_lcd_dog();
        }
      else if(keycode == 9)
        {
          printf("9");
          update_lcd_dog();
        }
      else if(keycode == 10)
        {
         // printf("A");
         // update_lcd_dog();
        }
      else if(keycode == 11)
        {
        //  printf("B");
        //  update_lcd_dog();
        }
      else if(keycode == 12)
        {
          printf("C");
          update_lcd_dog();
          clear_dsp();
          printf("FREQ KHZ = ");
          update_lcd_dog();
        }
      else if(keycode == 13)
        {
        //  printf("D");
        //  update_lcd_dog();
        }
      else if(keycode == 14)
        {
         // printf("E");
         // update_lcd_dog();
        }
      else if(keycode == 15)
        {
        //  printf("F");
        //  update_lcd_dog();
          temp = 1;
        }
      else
      {
        printf("");       //anything but the else must be there
      }
    
    keycode = 16;       //unpressed state
    __delay_cycles(1000000);    //delay to give button enough time to be released
  
      
      
      //printf("FREQ KHZ = %d", keycode);
      
      //clear_dsp() everytime keyvalue is pressed and freq changes
      //reprint FREQ KHZ right after then resume loop
      //if 15 then CLEAR and 
      //dsp_buff_1[10] = 0;
      //IF ENTER then drop down
  while(temp == 1)
  {    
      __disable_interrupt();
      dig1 = (dsp_buff_1[11] -48);
      dig2 = (dsp_buff_1[12] -48);
      dig3 = (dsp_buff_1[13] -48);
      dig4 = (dsp_buff_1[14] -48);
      
      if(dig4 <= 9)
      {
        dig2 = dig2 * 100;
        dig3 = dig3 * 10;
        dig1 = dig1 * 1000;
        dig1 = dig1 + dig2;
        dig1 = dig1 + dig3;
        dig1 = dig1 + dig4;
        printf(" DUTY CYCLE = ");
      }
      else if(dig3 <= 9)
      {
        dig2 = dig2 * 10;
        dig1 = dig1 * 100;        
        dig1 = dig1 + dig2;
        dig1 = dig1 + dig3;
        printf("  DUTY CYCLE = ");
      }
      else if(dig2 <= 9)
      {
        dig1 = dig1 * 10;
        dig1 = dig1 + dig2;
        printf("   DUTY CYCLE = ");
      }
      
      update_lcd_dog();
      
      keyval = 16000 / dig1;
      
      keyval = keyval - 1;
      
      __delay_cycles(1000000);
      __enable_interrupt();
 while(temp2 == 0)
 {
      
      if(keycode == 0)
        {
          putchar('0');
          update_lcd_dog();
        }
      else if(keycode == 1)
        {
          putchar('1');
          update_lcd_dog();
        }
      else if(keycode == 2)
        {
          printf("2");
          update_lcd_dog();
        }
      else if(keycode == 3)
        {
          printf("3");
          update_lcd_dog();
        }
      else if(keycode == 4)
        {
          printf("4");
          update_lcd_dog();
        }
      else if(keycode == 5)
        {
          printf("5");
          update_lcd_dog();
        }
      else if(keycode == 6)
        {
          printf("6");
          update_lcd_dog();
        }
      else if(keycode == 7)
        {
          printf("7");
          update_lcd_dog();
        }
      else if(keycode == 8)
        {
          printf("8");
          update_lcd_dog();
        }
      else if(keycode == 9)
        {
          printf("9");
          update_lcd_dog();
        }
      else if(keycode == 10)
        {
         // printf("A");
         // update_lcd_dog();
        }
      else if(keycode == 11)
        {
        //  printf("B");
        //  update_lcd_dog();
        }
      else if(keycode == 12)
        {
          printf("C");
          update_lcd_dog();
          clear_dsp();
          printf("FREQ KHZ = ");
          update_lcd_dog();
        }
      else if(keycode == 13)
        {
        //  printf("D");
        //  update_lcd_dog();
        }
      else if(keycode == 14)
        {
         // printf("E");
         // update_lcd_dog();
        }
      else if(keycode == 15)
        {
        //  printf("F");
        //  update_lcd_dog();
          temp2 = 1;
        }
      else
      {
        printf("");       //anything but the else must be there
      }
    
    keycode = 16;       //unpressed state
    __delay_cycles(1000000);    //delay to give button enough time to be released
      
 }   
 
      dig1a = (dsp_buff_2[13] -48);
      dig2a = (dsp_buff_2[14] -48);
       
      if(dig2a <= 9)
      {
        dig1a = dig1a * 10;
        dig1a = dig1a + dig2a;
      }
      
      OCR1B = 655 * dig1a;
      
      dig1a = 100 / dig1a;
 
      OCR1A = keyval;
      
     // OCR1B = keyval * (dig1a-1);
      
     // OCR1B = 65535 / dig1a;
      
      TCCR1A = (1<<CS10) | (0<<CS11) | (0<<CS12) | (0<<WGM11) | (1<<COM1A1) | (0<<COM1A0) | (1<<COM1B1) | (1<<COM1B0);
      TCCR1B = (0<<CS10) | (0<<CS11) | (1<<CS12) | (1<<WGM12);
      
     // __enable_interrupt();
      while(temp == 1)
      {
        //temp = 0;
        //temp = ~PD0;
        __delay_cycles(100);
        if(keycode == 0x0C)
        {
          temp = 0;
          clear_dsp();
          update_lcd_dog();
          TCCR1A = (0<<CS10) | (0<<CS11) | (0<<CS12) | (0<<WGM11);
          printf("FREQ KHZ = ");
          update_lcd_dog();
        }
      }
      //convert buff values to keycodeVal
      //8000 / (1 + keycodeVal) = freq in khz;
      //OCR = keycodeval;
      //SEPARATE LOOP WHERE TIMER IS STARTED AND LOOPED. KEYPAD INTERRUPT BRINGS BACK TO TOP LOOP
      //IF KEYCODE IS ENTER THEN DROP DOWN TO HERE
    }
    
    }
  
}