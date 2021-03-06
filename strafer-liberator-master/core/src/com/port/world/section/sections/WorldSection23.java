package com.port.world.section.sections;

import com.port.entity.mover.npc.hostile.Goblin;
import com.port.entity.mover.player.Player;
import com.port.world.PlayWorld;
import com.port.world.Scroller;
import com.port.world.section.WorldSection;
import com.port.world.structure.PereteInvizibil;

import greenfoot.*;  

public class WorldSection23 extends WorldSection{
   PlayWorld world;
   Scroller scroller;
   Player player;
    
    
    public WorldSection23(PlayWorld world,Scroller scroller,Player player){
        this.world=world;
        this.scroller=scroller;
        this.player=player;
        
    }
    
      public void init(){
       initNpc();
       init1();
       init2();
       init3();
       init4();
       init5();

       
   }public void initNpc(){
//npc
world.initObject(new Goblin(scroller, 1024, 3712),1024, 3712);
world.initObject(new Goblin(scroller, 1344, 2880),1344, 2880);
world.initObject(new Goblin(scroller, 1408, 1984),1408, 1984);
world.initObject(new Goblin(scroller, 1472, 3968),1472, 3968);
world.initObject(new Goblin(scroller, 1920, 1408),1920, 1408);
world.initObject(new Goblin(scroller, 2560, 960),2560, 960);
world.initObject(new Goblin(scroller, 2560, 1408),2560, 1408);
world.initObject(new Goblin(scroller, 3072, 1088),3072, 1088);
world.initObject(new Goblin(scroller, 3200, 768),3200, 768);
world.initObject(new Goblin(scroller, 3968, 832),3968, 832);
world.initObject(new Goblin(scroller, 4928, 640),4928, 640);
world.initObject(new Goblin(scroller, 4928, 1152),4928, 1152);
world.initObject(new Goblin(scroller, 5312, 4480),5312, 4480);
world.initObject(new Goblin(scroller, 5696, 1344),5696, 1344);
world.initObject(new Goblin(scroller, 5824, 576),5824, 576);
world.initObject(new Goblin(scroller, 6272, 3392),6272, 3392);
world.initObject(new Goblin(scroller, 6464, 3008),6464, 3008);
world.initObject(new Goblin(scroller, 6528, 3008),6528, 3008);
world.initObject(new Goblin(scroller, 6656, 1920),6656, 1920);
world.initObject(new Goblin(scroller, 6848, 896),6848, 896);
world.initObject(new Goblin(scroller, 7104, 2688),7104, 2688);
world.initObject(new Goblin(scroller, 7168, 3520),7168, 3520);
world.initObject(new Goblin(scroller, 7616, 1728),7616, 1728);
//npc
}



public void init1(){
//Walls
world.initObject(new PereteInvizibil("S", 1,"mic"), 32, 7544);
world.initObject(new PereteInvizibil("W", 1,"mic"), 32, 8008);
world.initObject(new PereteInvizibil("S", 1,"mic"), 96, 7544);
world.initObject(new PereteInvizibil("W", 1,"mic"), 96, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 160, 7544);
world.initObject(new PereteInvizibil("W", 1,"mic"), 160, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 224, 7480);
world.initObject(new PereteInvizibil("W", 1,"mic"), 224, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 288, 7480);
world.initObject(new PereteInvizibil("W", 1,"mic"), 288, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 352, 7416);
world.initObject(new PereteInvizibil("W", 1,"mic"), 352, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 416, 7416);
world.initObject(new PereteInvizibil("W", 1,"mic"), 416, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 480, 7352);
world.initObject(new PereteInvizibil("W", 1,"mic"), 480, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 544, 7352);
world.initObject(new PereteInvizibil("W", 1,"mic"), 544, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 608, 7352);
world.initObject(new PereteInvizibil("W", 1,"mic"), 608, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 672, 7288);
world.initObject(new PereteInvizibil("W", 1,"mic"), 672, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 736, 7288);
world.initObject(new PereteInvizibil("W", 1,"mic"), 736, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 800, 7288);
world.initObject(new PereteInvizibil("W", 1,"mic"), 800, 8136);
world.initObject(new PereteInvizibil("S", 1,"mic"), 864, 7288);
world.initObject(new PereteInvizibil("W", 1,"mic"), 864, 8136);
world.initObject(new PereteInvizibil("S", 1,"mic"), 928, 7288);
world.initObject(new PereteInvizibil("W", 1,"mic"), 928, 8136);
world.initObject(new PereteInvizibil("S", 1,"mic"), 992, 7288);
world.initObject(new PereteInvizibil("W", 1,"mic"), 992, 8136);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1056, 7224);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1056, 8136);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1120, 7160);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1120, 8136);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1184, 7160);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1184, 8136);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1248, 7160);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1248, 8136);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1312, 7096);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1312, 8136);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1376, 7096);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1376, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1440, 7032);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1440, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1504, 7032);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1504, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1568, 7032);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1568, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1632, 7032);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1632, 8008);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1696, 7032);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1696, 8008);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1760, 7032);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1760, 8008);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1824, 7032);
world.initObject(new PereteInvizibil("A", 1,"mic90"), 1800, 7968);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1888, 7032);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1888, 7880);
world.initObject(new PereteInvizibil("S", 1,"mic"), 1952, 7032);
world.initObject(new PereteInvizibil("W", 1,"mic"), 1952, 7880);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2016, 7032);
world.initObject(new PereteInvizibil("W", 1,"mic"), 2016, 7880);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2080, 7032);
world.initObject(new PereteInvizibil("W", 1,"mic"), 2080, 7880);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2144, 7032);
world.initObject(new PereteInvizibil("W", 1,"mic"), 2144, 7880);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2208, 6968);
world.initObject(new PereteInvizibil("W", 1,"mic"), 2208, 7880);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2272, 6968);
world.initObject(new PereteInvizibil("W", 1,"mic"), 2272, 7880);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2336, 6968);
world.initObject(new PereteInvizibil("W", 1,"mic"), 2336, 7880);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2400, 6904);
world.initObject(new PereteInvizibil("W", 1,"mic"), 2400, 7944);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2464, 6904);
world.initObject(new PereteInvizibil("W", 1,"mic"), 2464, 8008);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2528, 6904);
world.initObject(new PereteInvizibil("W", 1,"mic"), 2528, 8072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2592, 6840);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2656, 6840);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2720, 6776);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2784, 6712);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2848, 6712);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2912, 6712);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2976, 6520);
world.initObject(new PereteInvizibil("W", 1,"mic"), 2976, 6536);
world.initObject(new PereteInvizibil("S", 1,"mic"), 2976, 6712);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3040, 6712);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3104, 6648);

}






public void init2(){
world.initObject(new PereteInvizibil("S", 1,"mic"), 3168, 6584);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3232, 6584);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3296, 6584);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3360, 6520);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3424, 6456);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3488, 6456);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3552, 6456);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3616, 6456);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3680, 6392);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3744, 6328);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3808, 6328);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3872, 6264);
world.initObject(new PereteInvizibil("S", 1,"mic"), 3936, 6264);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4000, 6200);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4064, 6200);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4128, 6200);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4192, 6136);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4256, 6136);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4320, 6072);

}






public void init3(){
world.initObject(new PereteInvizibil("S", 1,"mic"), 4384, 6072);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4448, 6008);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4512, 6008);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4576, 6008);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4640, 5944);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4704, 5944);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4768, 5880);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4832, 5880);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4896, 5816);
world.initObject(new PereteInvizibil("S", 1,"mic"), 4960, 5816);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5024, 5688);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5088, 5688);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5152, 5624);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5216, 5624);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5280, 5560);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5344, 5560);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5408, 5496);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5472, 5432);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5536, 5368);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5600, 5304);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5664, 5304);

}






public void init4(){
world.initObject(new PereteInvizibil("S", 1,"mic"), 5728, 5304);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5792, 5240);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5856, 5176);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5920, 5112);
world.initObject(new PereteInvizibil("S", 1,"mic"), 5984, 5112);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6048, 5048);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6112, 5048);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6176, 4984);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6240, 4920);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6304, 4856);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6368, 4856);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6432, 4792);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6496, 4792);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6560, 4728);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6624, 4728);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6688, 4664);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6752, 4664);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6816, 4600);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6880, 4536);
world.initObject(new PereteInvizibil("S", 1,"mic"), 6944, 4472);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7008, 4408);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7072, 4344);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7136, 4344);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7200, 4280);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7264, 4216);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7328, 4152);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7392, 4088);

}






public void init5(){
world.initObject(new PereteInvizibil("S", 1,"mic"), 7456, 4024);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7520, 3896);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7584, 3832);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7648, 3768);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7712, 3640);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7776, 3448);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7840, 3320);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7904, 3192);
world.initObject(new PereteInvizibil("S", 1,"mic"), 7968, 3128);
world.initObject(new PereteInvizibil("S", 1,"mic"), 8032, 3064);
world.initObject(new PereteInvizibil("S", 1,"mic"), 8096, 3000);
world.initObject(new PereteInvizibil("S", 1,"mic"), 8160, 2872);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 56, 8032);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 184, 7520);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 312, 7456);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 440, 7392);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 632, 7328);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 760, 8096);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 1016, 7264);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 1080, 7200);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 1272, 7136);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 1400, 7072);
world.initObject(new PereteInvizibil("A", 1,"mic90"), 1352, 8096);
world.initObject(new PereteInvizibil("A", 1,"mic90"), 1608, 8032);
world.initObject(new PereteInvizibil("A", 1,"mic90"), 1864, 7904);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 2168, 7008);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 2360, 6944);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 2360, 7904);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 2424, 7968);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 2488, 8032);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 2552, 6880);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 2552, 8096);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 2552, 8160);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 2680, 6816);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 2744, 6752);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 3064, 6688);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 3128, 6624);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 3320, 6560);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 3384, 6496);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 3640, 6432);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 3704, 6368);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 3832, 6304);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 3960, 6240);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 4152, 6176);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 4280, 6112);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 4408, 6048);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 4600, 5984);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 4728, 5920);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 4856, 5856);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 4984, 5728);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 4984, 5792);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 5112, 5664);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 5240, 5600);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 5368, 5536);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 5432, 5472);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 5496, 5408);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 5560, 5344);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 5752, 5280);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 5816, 5216);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 5880, 5152);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 6008, 5088);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 6136, 5024);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 6200, 4960);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 6264, 4896);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 6392, 4832);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 6520, 4768);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 6648, 4704);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 6776, 4640);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 6840, 4576);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 6904, 4512);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 6968, 4448);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7032, 4384);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7160, 4320);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7224, 4256);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7288, 4192);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7352, 4128);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7416, 4064);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7480, 3936);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7480, 4000);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7544, 3872);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7608, 3808);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7672, 3680);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7672, 3744);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7736, 3488);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7736, 3552);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7736, 3616);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7800, 3360);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7800, 3424);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7864, 3232);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7864, 3296);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7928, 3168);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 7992, 3104);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 8056, 3040);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 8120, 2912);
world.initObject(new PereteInvizibil("D", 1,"mic90"), 8120, 2976);
//Walls
}
}