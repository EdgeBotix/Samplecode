# enables eBot to play a song on its buzzer based on an input

# Copyright (c) 2014, Erik Wilhelm
# All rights reserved.

# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are met:
# 1. Redistributions of source code must retain the above copyright
   # notice, this list of conditions and the following disclaimer.
# 2. Redistributions in binary form must reproduce the above copyright
   # notice, this list of conditions and the following disclaimer in the
   # documentation and/or other materials provided with the distribution.
# 3. All advertising materials mentioning features or use of this software
   # must display the following acknowledgement:
   # This product includes software developed by the <organization>.
# 4. Neither the name of the SUTD nor Edgebotix nor the
   # names of its contributors may be used to endorse or promote products
   # derived from this software without specific prior written permission.

# THIS SOFTWARE IS PROVIDED BY ERIK WILHELM ''AS IS'' AND ANY
# EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
# WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
# DISCLAIMED. IN NO EVENT SHALL ERIK WILHELM BE LIABLE FOR ANY
# DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
# (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
# LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
# ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
# (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

"""
Plays a list of songs. Input a number to choose.
1. Michigan fight song
2. Intro to Sweet Child of Mine
3. Mario Theme Song

Uses notes.py, an add-on library that simplifies buzzer song creation.
Thanks to Justas Sadvecius for the library!

The Finch is a robot for computer science education. Its design is the result
of a four year study at Carnegie Mellon's CREATE lab.

http://www.finchrobot.com
"""

from finch import Finch
from time import sleep
import notes

#Main function for the music player example program"""

#Initialize the finch    
finch = Finch()

songList = ['E5  C D E C D E F  D E F D E F G  A  GE F C D E G E D C ',
                'D D5 A4 G G5 A4 F5# A4 D D5 A4 G G5 A4 F5# A4 '
                'E D5 A4 G G5 A4 F5# A4 E D5 A4 G G5 A4 F5# A4 '
                'G D5 A4 G G5 A4 F5# A4 G D5 A4 G G5 A4 F5# A4 '
                'D D5 A4 G G5 A4 F5# A4 D D5 A4 G G5 A4 F5# A4 ',
                'E5 E  E   C E  G    G4   C5  G4   E   A BBb A  G  '
                'E5  G  A F G  E  C D B4  C5  G4   E   A BBb A  G  '
                'E5  G  A F G  E  C D B4 -  G5 Gb F D#  E G4# A C5 A4 C5 D '
                'G5 Gb F D#  E C6 C6 C6   '
                'G5 Gb F D#  E G4# A C5 A4 C5 D  Eb  D  C    '
                ' G5 Gb F D#  E G4# A C5 A4 C5 D G5 Gb F D#  E C6 C C  ']
timeList = [0.18,0.1,0.1]

song = 1
while song > 0 and song < 4:
    #get which song
    song = int(input("Enter 1 for the Michigan fight song, 2 for Sweet Child of Mine,"
                    "3 for the Mario theme song; any other number to exit."))

    if song >=1 and song <= 3:
        notes.sing(finch, songList[song -1],timeList[song-1])
    else:
        print('Exiting...')
