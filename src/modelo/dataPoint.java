/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author michael
 */
public class dataPoint {
    protected int idDataPoint;
    protected int addressL;
    protected int addressH;
    protected int lengthL;
    protected int lengthH;
    protected int[] dataL;
    protected int[] dataH;
    
    public dataPoint(int id, int ah,int al,int lh,int ll){
        idDataPoint = id;
        addressL = al;
        addressH = ah;
        lengthL = ll;
        lengthH = lh;
        if(ll >=0)
            dataL = new int[ll];
        if(lh >=0)
            dataL = new int[lh];
    }
    
    public void setIdDataPoint(int value){
            idDataPoint = value;
    }
    
    public void setDataL(int data[]){
        if(data.length == lengthL){
            dataL = data;
        }
    }
    
    public void setDataH(int data[]){
        if(data.length == lengthH){
            dataH = data;
        }
    }
    
    public void setDataL(int address, int value){
        if(address >= addressL && address <= addressL+lengthL){
            dataL[address-addressL] = value;
        }else{
            if(address <= dataL.length && address > 0){
                dataL[address-1] = value;
            }
        }
    }
    
    
    public void setDataH(int address, int value){
        if(address >= addressH && address < addressH+lengthH){
            dataH[address-addressH] = value;
        }else{
            if(address <= dataH.length && address > 0){
                dataH[address-1] = value;
            }
        }
    }
    
    public int getIdDataPoint(){
        return idDataPoint;
    }
    
    
    public int getDataL(int address){
        if(address >= addressL && address < addressL+lengthL){
            return dataL[address-addressL];
        }else{
            if(address <= dataL.length && address > 0){
                return dataL[address-1];
            }
        }
        return 0;
    }
    
    public int getDataH(int address){
        
        
        if(address >= addressH && address < addressH+lengthH){
            return dataH[address-addressH];
        }else{
            if(address <= dataH.length && address > 0){
                return dataH[address-1];
            }
        }
        return 0;
    }
    
    public int getAddressH(){
        return addressH;
    }
    
    public int getAddressL(){
        return addressL;
    }
    
    public int getLengthH(){
        return lengthH;
    }
    
    public int getLengthL(){
        return lengthL;
    }
    
}
