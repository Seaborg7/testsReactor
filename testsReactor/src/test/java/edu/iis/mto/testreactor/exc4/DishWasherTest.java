package edu.iis.mto.testreactor.exc4;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class DishWasherTest {
    
    @Mock
    DirtFilter dirtFilter;
    @Mock
    Door door;
    @Mock
    Engine engine;
    @Mock
    WaterPump waterPump;
    
    DishWasher dishWasher;
    ProgramConfiguration programConfiguration;
    RunResult runResult;
    
    @Before
    public void init() 
    {
        MockitoAnnotations.initMocks(this);
        dishWasher = new DishWasher(waterPump, engine, dirtFilter, door);
        programConfiguration = ProgramConfiguration.builder()
                                                   .withProgram(any(WashingProgram.class))
                                                   .withTabletsUsed(true)
                                                   .build();
    }

    @Test
    public void itCompiles() {
        assertThat(true, Matchers.equalTo(true));
    }
    
    @Test
    public void StartingWashWithOpenedDoorShouldReturnDOOR_OPEN_ERROR() {
        Mockito.when(door.closed()).thenReturn(false);
        runResult = dishWasher.start(programConfiguration);
        assertEquals(Status.DOOR_OPEN_ERROR, runResult.getStatus());
    }

}
